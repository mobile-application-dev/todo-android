package com.example.todo.model

import java.time.LocalDate

data class Note(
    var id: Long,
    var userId: Long,
    var title: String,
    var body: String,
    var date: LocalDate,
    var longitude: Double,
    var latitude: Double
)