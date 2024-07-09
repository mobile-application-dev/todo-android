package com.example.todo.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.db.NoteDatabase
import com.example.todo.repository.NoteKeeperMemory
import com.example.todo.repository.NoteRepository

class NoteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            val database = NoteDatabase.getInstance(context)
            val keeper = database.noteDao // Room data source
//            val keeper = NoteKeeperMemory() // Memory data source
            val repository = NoteRepository(keeper)
            return NoteViewModel(repository) as T
        }
        return super.create(modelClass)
    }
}