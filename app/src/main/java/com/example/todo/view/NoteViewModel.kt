package com.example.todo.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.model.Note
import com.example.todo.repository.NoteRepository
import java.time.LocalDate

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
    val id = MutableLiveData<Long>(0)
    var title = MutableLiveData<String>()
    var body = MutableLiveData<String>()
    var date = MutableLiveData<String>()

    val notes = repository.getNotes()

    fun clear() {
        id.value = 0
        title.value = ""
        body.value = ""
        date.value = ""
    }

    fun insert() {
        if (title.value.isNullOrEmpty() || body.value.isNullOrEmpty()) return
        val note = getCurrentNote()
        repository.insert(note)
        clear()
    }

    fun cancel() {
        clear()
    }

    fun delete() {
        repository.delete(getCurrentNote())
        notes.value = repository.getNotes().value
        clear()
    }

    fun update() {
        repository.update(getCurrentNote())
        clear()
    }

    private fun getCurrentNote(): Note{
        return Note(
            id.value!!,
            1,
            title.value!!,
            body.value!!,
            LocalDate.now(),
            0.0,
            0.0
        )
    }
}