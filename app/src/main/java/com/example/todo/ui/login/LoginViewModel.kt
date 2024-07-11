package com.example.todo.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import com.example.todo.data.LoginRepository
import com.example.todo.data.Result
import com.example.todo.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm
    val loginSession = MutableLiveData("")
    private val key = stringPreferencesKey("user_session")
    private val loginFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[key] ?: ""
    }

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private fun createDataStore(username: String) = viewModelScope.launch {
        dataStore.edit { settings ->
            settings[key] = username
        }
    }

    private fun logIn(username: String, password: String) = viewModelScope.launch {
        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.userId))
            createDataStore(result.data.userId)
            loginSession.value = result.data.userId
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }

    }

    fun verifyLogin() = viewModelScope.launch {
        loginFlow.collect {
            loginSession.value = (it)
        }
    }

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        logIn(username, password)
    }

    fun logout() {
        _loginResult.value = LoginResult(success = null, error = null)
        createDataStore("")
        loginSession.value = ""
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            false
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}