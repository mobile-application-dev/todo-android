package com.example.todo.service

import com.example.todo.model.Login
import com.example.todo.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("/login")
    fun login(@Body login: Login): Call<LoginResponse>
}