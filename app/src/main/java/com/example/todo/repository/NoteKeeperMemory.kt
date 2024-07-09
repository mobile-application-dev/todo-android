package com.example.todo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.todo.model.Note
import java.time.LocalDate

class NoteKeeperMemory : NoteKeeper {

    private val notes = MutableLiveData<ArrayList<Note>>()

    init {
        notes.value = arrayListOf(
            Note(
                1,
                1,
                "Note 1",
                "This is the first note",
                LocalDate.parse("2021-09-01"),
                0.0,
                0.0
            ),
            Note(
                2,
                1,
                "Note 2",
                "This is the second note",
                LocalDate.parse("2021-09-02"),
                0.0,
                0.0
            ),
            Note(
                3,
                1,
                "Note 3",
                "This is the third note",
                LocalDate.parse("2021-09-03"),
                0.0,
                0.0
            )
        )
    }

    override suspend fun insert(note: Note) {
        note.id = notes.value?.size?.toLong() ?: 0
        notes.value?.add(note)
        notes.value = notes.value
    }

    override suspend fun update(note: Note) {
        val index = notes.value?.indexOfFirst { it.id == note.id }
        if (index != null && index >= 0) {
            notes.value?.set(index, note)
            notes.value = notes.value
        }
    }

    override suspend fun delete(note: Note) {
        notes.value?.remove(note)
        notes.value = notes.value
    }

    override fun getNotes(): LiveData<List<Note>> {
        return notes.map { it }
    }
}