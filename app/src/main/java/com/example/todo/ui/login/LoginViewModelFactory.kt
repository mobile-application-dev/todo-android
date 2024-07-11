package com.example.todo.ui.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.data.LoginDataSource
import com.example.todo.data.LoginRepository
import com.example.todo.service.LoginApiService
import com.example.todo.service.RetrofitFactory

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory(private val dataStore: DataStore<Preferences>) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val service = RetrofitFactory.createRetrofit().create(LoginApiService::class.java)
            val repository = LoginRepository(LoginDataSource(service))
            return LoginViewModel(
                repository, dataStore
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}