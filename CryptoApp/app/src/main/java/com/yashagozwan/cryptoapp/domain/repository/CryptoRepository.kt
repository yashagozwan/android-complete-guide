package com.yashagozwan.cryptoapp.domain.repository

import com.yashagozwan.cryptoapp.domain.model.CoinModel

interface CryptoRepository {
    fun getHello(): String

    fun getCoins(): List<CoinModel>
}