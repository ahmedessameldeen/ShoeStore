package com.udacity.shoestore.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R

class LoginViewModel() : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        if (validateCredentials(username, password)) {
            _loginResult.value = LoginResult(success = LoggedInUserView(username))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun validateCredentials(username: String, password: String): Boolean {
        return if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
            false
        } else if (!isPasswordValid(password)) {
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