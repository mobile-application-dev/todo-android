package com.example.todo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Login(
    @SerializedName("username")
    var username: String,
    @SerializedName("password")
    var password: String
) : Parcelable

data class LoginResponse(@SerializedName("user_id") val userId: String)
