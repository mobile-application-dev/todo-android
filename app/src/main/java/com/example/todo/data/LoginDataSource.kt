package com.example.todo.data

import com.example.todo.data.model.LoggedInUser
import com.example.todo.model.Login
import com.example.todo.service.LoginApiService
import com.google.gson.annotations.SerializedName
import retrofit2.awaitResponse
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(private val loginApiService: LoginApiService) {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            val login = Login(username, password)
            val result = loginApiService.login(login)
            val response = result.awaitResponse()
            println("response: ${response.body()?.userId} ${response.code()} ${response.message()}")

            if (!response.isSuccessful) {
                return Result.Error(IOException("Error logging in: ${response.code()} ${response.message()}"))
            }
            return Result.Success(LoggedInUser(response.body()?.userId!!, response.message()))
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}
