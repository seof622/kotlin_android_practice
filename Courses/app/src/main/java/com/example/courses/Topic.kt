package com.example.courses

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringSource: Int,
    val courseNumber: Int,
    @DrawableRes val imageSource: Int
)
