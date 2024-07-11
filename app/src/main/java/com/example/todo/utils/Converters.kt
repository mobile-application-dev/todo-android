package com.example.todo.utils

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Converters {
    private val dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    @TypeConverter
    fun fromTimestamp(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it, dateFormat) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): String? {
        // format to YYYY/MM/DD
        return date?.format(dateFormat)
    }
}