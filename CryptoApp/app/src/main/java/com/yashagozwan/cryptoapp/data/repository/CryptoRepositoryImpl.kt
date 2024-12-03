package com.yashagozwan.cryptoapp.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yashagozwan.cryptoapp.R
import com.yashagozwan.cryptoapp.domain.model.CoinModel
import com.yashagozwan.cryptoapp.domain.repository.CryptoRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
) : CryptoRepository {
    override fun getHello(): String {
        return "Mantap Jiwa"
    }

    override fun getCoins(): List<CoinModel> {
        val stream = context.resources.openRawResource(R.raw.data)
        val json = stream.bufferedReader().use { it.readText() }
        val coins = object : TypeToken<List<CoinModel>>() {}.type
        return gson.fromJson(json, coins)
    }
}