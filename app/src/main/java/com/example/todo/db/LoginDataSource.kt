package com.example.todo.db

import com.example.todo.data.Result
import com.example.todo.model.UserData
import java.io.IOException
import java.util.UUID

class LoginDataSource {

    fun login(username: String, password: String): Result<UserData> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = UserData(UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}