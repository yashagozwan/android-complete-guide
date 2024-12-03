package com.yashagozwan.cryptoapp.data.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.http.headers
import javax.inject.Inject

class CryptoApi @Inject constructor(
    private val httpClient: HttpClient,
) {
    fun client(): HttpClient {
        return httpClient.config {
            defaultRequest {
                url("https://jsonplaceholder.typicode.com")
                headers.append(HttpHeaders.ContentType, "application/json")
            }
        }
    }
}