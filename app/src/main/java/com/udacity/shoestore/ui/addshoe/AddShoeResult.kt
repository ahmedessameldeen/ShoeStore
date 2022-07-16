package com.udacity.shoestore.ui.addshoe

import com.udacity.shoestore.models.Shoe


data class AddShoeResult(
    val success: Shoe? = null,
    val error: Int? = null
)