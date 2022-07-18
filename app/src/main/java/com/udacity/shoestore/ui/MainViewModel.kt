package com.udacity.shoestore.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel() : ViewModel() {

    private val _shoesList: MutableLiveData<MutableList<Shoe>> = MutableLiveData(mutableListOf())
    val shoesList: LiveData<MutableList<Shoe>> = _shoesList

    fun addShoe(shoe: Shoe) {
        _shoesList.value = (_shoesList.value?: mutableListOf()).plus(shoe).toMutableList()
    }

    fun logout() {
        _shoesList.value = mutableListOf()
    }
}