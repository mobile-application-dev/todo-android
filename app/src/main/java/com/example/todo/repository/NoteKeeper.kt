package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.model.Note

interface NoteKeeper {
    suspend fun insert(note: Note)
    suspend fun update(note: Note)
    suspend fun delete(note: Note)
    fun getNotes(): LiveData<List<Note>>
    suspend fun insertAll(notes: List<Note>)

}