package com.example.todo.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.model.Note
import com.example.todo.repository.NoteRepository
import java.time.LocalDate

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
    val title = MutableLiveData<String>()
    var body = MutableLiveData<String>()

    val notes = repository.getNotes()

    private fun clear() {
        title.value = ""
        body.value = ""
    }

    fun insert() {
        if (title.value.isNullOrEmpty() || body.value.isNullOrEmpty()) return
        val note = Note(
            0,
            1,
            title.value!!,
            body.value!!,
            LocalDate.now(),
            0.0,
            0.0
        )
        repository.insert(note)
        clear()
    }

    fun cancel() {
        clear()
    }

}