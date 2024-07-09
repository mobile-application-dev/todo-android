package com.example.todo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.todo.model.Note
import java.time.LocalDate
import java.util.ArrayList

class NoteRepository(private val keeper: NoteKeeper) {

    fun getNotes(): LiveData<ArrayList<Note>> {
        return keeper.getNotes().map { it as ArrayList<Note> }
    }

    suspend fun insert(note: Note) {
        keeper.insert(note)
    }

    suspend fun update(note: Note) {
        keeper.update(note)
    }

    suspend fun delete(note: Note) {
        keeper.delete(note)
    }
}