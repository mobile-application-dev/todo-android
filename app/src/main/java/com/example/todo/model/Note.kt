package com.example.todo.model

import android.os.Parcelable
import java.time.LocalDate
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    var id: Long,
    var userId: Long,
    var title: String,
    var body: String,
    var date: LocalDate,
    var longitude: Double,
    var latitude: Double
) : Parcelable
