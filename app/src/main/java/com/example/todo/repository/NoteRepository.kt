package com.example.todo.repository

import com.example.todo.model.Note
import kotlinx.coroutines.flow.flow

class NoteRepository(
    private val keeper: NoteKeeper,
    private val apiRepository: NoteApiRepository
) {
    val notes = keeper.getNotes()

    fun getAllNotes() = flow {
        val response = apiRepository.getNotes()
        if (response.isSuccessful) {
            response.result?.let {
                keeper.insertAll(it)
            }
            emit(true)
        } else {
            emit(false)
        }
    }

    suspend fun insert(note: Note) {
        apiRepository.insert(note)
    }

    suspend fun update(note: Note) {
        apiRepository.update(note)
        keeper.update(note)
    }

    suspend fun delete(note: Note) {
        apiRepository.delete(note)
        keeper.delete(note)
    }
}