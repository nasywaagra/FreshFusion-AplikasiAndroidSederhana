package com.dicoding.freshfusion

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Drink (
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable