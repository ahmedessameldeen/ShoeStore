package com.udacity.shoestore.ui.instructions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class InstructionsViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InstructionsViewModel::class.java)) {
            return InstructionsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}