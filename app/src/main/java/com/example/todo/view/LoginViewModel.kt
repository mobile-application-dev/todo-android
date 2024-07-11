package com.example.todo.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.todo.repository.LoginRepository
import com.example.todo.data.Result
import com.example.todo.R
import com.example.todo.model.UserData
import com.example.todo.model.LoggedInUserView
import com.example.todo.data.LoginResult

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    val loginResult = MutableLiveData<LoginResult>()
    val user = MutableLiveData<UserData>()

    fun isLogged (): Boolean {
        val result = loginRepository.isLoggedIn
        if (result){
            user.value = loginRepository.user
        }
        return result
    }
    fun login(username: String, password: String): MutableLiveData<LoginResult> {
        if (!isUserNameValid(username)) {
            loginResult.value = LoginResult(error = R.string.invalid_username)
            return loginResult
        }
        if (!isPasswordValid(password)) {
            loginResult.value = LoginResult(error = R.string.invalid_password)
            return loginResult
        }

        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            loginResult.value = LoginResult(error = R.string.login_failed)
        }
        return loginResult
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else { false }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun logout() {
        user.value?.userId = ""
        user.value?.displayName = ""
        loginRepository.logout()
    }
}