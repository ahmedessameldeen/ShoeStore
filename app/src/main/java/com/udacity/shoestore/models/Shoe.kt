package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(
    var name: String = "",
    var size: Double = 0.0,
    var company: String = "",
    var description: String = "",
    var price: String = "122.99",
    val images: List<String> = mutableListOf()
) : Parcelable {
    val mainImage = images.firstOrNull() ?: "https://picsum.photos/200/200"
}