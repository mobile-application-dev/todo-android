package com.example.todo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var userId: Long,
    var title: String,
    var body: String,
    var date: LocalDate,
    var longitude: Double,
    var latitude: Double
) : Parcelable
