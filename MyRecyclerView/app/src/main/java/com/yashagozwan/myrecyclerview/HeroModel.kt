package com.yashagozwan.myrecyclerview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroModel(
    val name: String,
    val description: String,
    val photo: String,
) : Parcelable
