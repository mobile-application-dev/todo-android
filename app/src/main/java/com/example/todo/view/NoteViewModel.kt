package com.example.todo.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.model.Note
import com.example.todo.repository.NoteKeeper
import com.example.todo.repository.NoteRepository
import kotlinx.coroutines.launch
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

    private fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    private fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    private fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }

    fun insert() {
        if (title.value.isNullOrEmpty() || body.value.isNullOrEmpty()) return
        val note = getCurrentNote()
        insert(note)
        clear()
    }

    fun cancel() {
        clear()
    }

    fun delete() {
        delete(getCurrentNote())
        clear()
    }

    fun update() {
        update(getCurrentNote())
        clear()
    }

    private fun getCurrentNote(): Note {
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