package com.udacity.shoestore.ui.addshoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class AddShoeViewModel() : ViewModel() {

    val name = MutableLiveData<String>()
    val company = MutableLiveData<String>()
    val size = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val desc = MutableLiveData<String>()

    private val _addShoeForm = MutableLiveData<AddShoeFormState>()
    val addShoeFormState: LiveData<AddShoeFormState> = _addShoeForm


    val _addShoeResult = MutableLiveData<AddShoeResult>()
    val addShoeResult: LiveData<AddShoeResult> = _addShoeResult

    fun addShoe() {
        val shoeName: String = name.value ?: ""
        val shoeCompany: String = company.value ?: ""
        val shoeSize: Double = size.value?.toDouble() ?: 0.0
        val shoePrice: Double = price.value?.toDouble() ?: 0.0
        val shoeDesc: String = desc.value ?: ""
        if (validateCredentials(shoeName, shoeCompany, shoeSize, shoePrice, shoeDesc)) {
            _addShoeResult.value = AddShoeResult(
                success = Shoe(
                    shoeName,
                    shoeSize,
                    shoeCompany,
                    shoeDesc,
                    shoePrice.toString()
                )
            )
        } else {
            _addShoeResult.value = AddShoeResult(error = R.string.addShoe_failed)
        }
    }

    fun validateCredentials(
        name: String,
        company: String,
        size: Double,
        price: Double,
        desc: String
    ): Boolean {
        return if (!isShoeNameValid(name)) {
            _addShoeForm.value = AddShoeFormState(shoeNameError = R.string.invalid_shoe_name)
            false
        } else if (!isShoeCompanyValid(company)) {
            _addShoeForm.value = AddShoeFormState(shoeCompanyError = R.string.invalid_shoe_company)
            false
        } else if (!isShoeSizeValid(size)) {
            _addShoeForm.value = AddShoeFormState(shoeSizeError = R.string.invalid_shoe_size)
            false
        } else if (!isShoePriceValid(price)) {
            _addShoeForm.value = AddShoeFormState(shoePriceError = R.string.invalid_shoe_price)
            false
        } else if (!isShoeDescriptionValid(desc)) {
            _addShoeForm.value =
                AddShoeFormState(shoeDescriptionError = R.string.invalid_shoe_description)
            false
        } else {
            _addShoeForm.value = AddShoeFormState(isDataValid = true)
            true
        }
    }


    private fun isShoeNameValid(name: String): Boolean {
        return name.length > 3
    }


    private fun isShoeCompanyValid(company: String): Boolean {
        return company.length > 3
    }

    private fun isShoeDescriptionValid(desc: String): Boolean {
        return desc.length > 3
    }

    private fun isShoeSizeValid(size: Double): Boolean {
        return size > 10 && size < 50
    }

    private fun isShoePriceValid(price: Double): Boolean {
        return price > 0 && price < 100000
    }
}