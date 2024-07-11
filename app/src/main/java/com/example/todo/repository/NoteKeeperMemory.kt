package com.example.todo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.todo.model.Note
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NoteKeeperMemory : NoteKeeper {

    private val notes = MutableLiveData<ArrayList<Note>>()
    private val dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    init {
        notes.value = arrayListOf(
            Note(
                "id1",
                "usuario8",
                "Note 1",
                "This is the first note",
                LocalDate.parse("2021-09-01").format(dateFormat),
                0.0,
                0.0
            ),
            Note(
                "id2",
                "usuario8",
                "Note 2",
                "This is the second note",
                LocalDate.parse("2021-09-02").format(dateFormat),
                0.0,
                0.0
            ),
            Note(
                "id3",
                "usuario8",
                "Note 3",
                "This is the third note",
                LocalDate.parse("2021-09-03").format(dateFormat),
                0.0,
                0.0
            )
        )
    }

    override suspend fun insert(note: Note) {
        note.id = "id" + notes.value?.size?.toLong()
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

    override suspend fun insertAll(notes: List<Note>) {
    }
}