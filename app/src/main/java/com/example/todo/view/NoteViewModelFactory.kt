package com.example.todo.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.repository.NoteRepository

class NoteViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            val repository = NoteRepository()
            return NoteViewModel(repository) as T
        }
        return super.create(modelClass)
    }
}