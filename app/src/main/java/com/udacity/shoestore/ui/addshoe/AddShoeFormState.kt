package com.udacity.shoestore.ui.addshoe


data class AddShoeFormState(
    val shoeNameError: Int? = null,
    val shoeCompanyError: Int? = null,
    val shoeSizeError: Int? = null,
    val shoePriceError: Int? = null,
    val shoeDescriptionError: Int? = null,
    val isDataValid: Boolean = false
)