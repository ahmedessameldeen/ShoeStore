package com.udacity.shoestore.ui.addshoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddShoeViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddShoeViewModel::class.java)) {
            return AddShoeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}