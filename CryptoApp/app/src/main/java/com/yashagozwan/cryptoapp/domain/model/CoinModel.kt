package com.yashagozwan.cryptoapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinModel(
    val id: String,
    val image: String,
    val symbol: String,
    val name: String,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24: Double,
    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24: Double,
    @SerializedName("ath_change_percentage")
    val athChangePercentage : Double,
    val atl: Double,
) : Parcelable