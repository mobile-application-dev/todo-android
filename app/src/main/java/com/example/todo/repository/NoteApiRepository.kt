package com.example.todo.repository

import android.util.Log
import com.example.todo.model.Note
import com.example.todo.response.CustomResponse
import com.example.todo.service.NoteApiService
import retrofit2.awaitResponse

class NoteApiRepository(private val service: NoteApiService) {
    suspend fun getNotes(): CustomResponse<List<Note>> {
        val response = service.getNotes("usuario8")
        return if (response.isSuccessful) {
            CustomResponse(response.body(), null)
        } else {
            CustomResponse(null, response.code())
        }
    }

    suspend fun insert(note: Note) {
        try {
            val result = service.postNote(note)
            result.awaitResponse()
        } catch (e: Exception) {
            Log.e("NoteApiRepository", e.message.toString())
        }
    }

    suspend fun update(note: Note) {
        try {
            val result = service.putNote(note)
            result.awaitResponse()
        } catch (e: Exception) {
            Log.e("NoteApiRepository", e.message.toString())
        }
    }

    suspend fun delete(note: Note) {
        try {
            val result = service.deleteNote(note)
            result.awaitResponse()
        } catch (e: Exception) {
            Log.e("NoteApiRepository", e.message.toString())
        }
    }

}