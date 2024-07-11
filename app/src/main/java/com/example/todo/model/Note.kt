package com.example.todo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey
    @SerializedName("id")
    var id: String = UUID.randomUUID().toString(),
    @SerializedName("user_id")
    var userId: String,
    @SerializedName("titulo")
    var title: String,
    @SerializedName("body")
    var body: String,
    @SerializedName("fecha")
    var date: String,
    @SerializedName("longitud")
    var longitude: Double,
    @SerializedName("latitud")
    var latitude: Double
) : Parcelable
