package com.example.todo.repository

import androidx.lifecycle.MutableLiveData
import com.example.todo.model.Note
import java.time.LocalDate
import java.util.ArrayList

class NoteRepository {
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

    fun getNotes(): MutableLiveData<ArrayList<Note>> {
        return notes
    }

    fun insert(note: Note) {
        note.id = notes.value?.size?.toLong() ?: 0
        notes.value?.add(note)
        notes.value = notes.value
    }

    fun delete(note: Note) {
        val index = notes.value?.indexOfFirst { it.id == note.id }
        if (index != null && index != -1) {
            notes.value?.remove(notes.value?.get(index))
        }
    }

    fun update(note: Note) {
        val index = notes.value?.indexOfFirst { it.id == note.id }

        if (index != null && index != -1) {
            notes.value?.set(index, note)
        }
    }
}