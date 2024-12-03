package com.yashagozwan.cryptoapp.core.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

object Utils {

    fun idr(price: Double): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID) as DecimalFormat
        numberFormat.applyPattern("Rp #,###")
        return numberFormat.format(price).toString()
    }
}