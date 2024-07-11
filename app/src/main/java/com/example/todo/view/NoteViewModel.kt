package com.example.todo.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.model.Note
import com.example.todo.repository.NoteRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

class NoteViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    val id = MutableLiveData(UUID.randomUUID().toString())
    var title = MutableLiveData<String>()
    var body = MutableLiveData<String>()
    var date = MutableLiveData<String>()
    var latitude = MutableLiveData<Double>(0.0)
    var longitude = MutableLiveData<Double>(0.0)

    val notes = noteRepository.notes

    fun getAllNotes() = viewModelScope.launch {
        noteRepository.getAllNotes().collect {
            Log.d("NoteViewModel", "Notes updated")
        }
    }

    fun clear() {
        id.value = "default_id"
        title.value = ""
        body.value = ""
        date.value = ""
    }

    private fun insert(note: Note) = viewModelScope.launch {
        noteRepository.insert(note)
        getAllNotes()
    }

    private fun update(note: Note) = viewModelScope.launch {
        noteRepository.update(note)
        getAllNotes()
    }

    private fun delete(note: Note) = viewModelScope.launch {
        noteRepository.delete(note)
        getAllNotes()
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
            "usuario8",
            title.value!!,
            body.value!!,
            LocalDate.now().format(dateFormat),
            longitude.value!!,
            latitude.value!!
        )
    }
}
