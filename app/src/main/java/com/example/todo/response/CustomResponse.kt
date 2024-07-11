package com.example.todo.response

class CustomResponse<T>(val result: T?, val errorCode: Int?) {
    val isSuccessful: Boolean
        get() = errorCode == null
}