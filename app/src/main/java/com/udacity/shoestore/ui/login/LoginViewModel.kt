package com.udacity.shoestore.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R

class LoginViewModel() : ViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login() {
        if (validateCredentials()) {
            _loginResult.value = LoginResult(success = LoggedInUserView(username.value ?: ""))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun validateCredentials(): Boolean {
        return if (!isUserNameValid(username.value ?: "")) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
            false
        } else if (!isPasswordValid(password.value ?: "")) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
            false
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
            true
        }
    }


    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }


    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}