package com.example.todo.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.db.NoteDatabase
import com.example.todo.repository.NoteApiRepository
import com.example.todo.repository.NoteRepository
import com.example.todo.service.NoteApiService
import com.example.todo.service.RetrofitFactory

class NoteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            val database = NoteDatabase.getInstance(context)
            val keeper = database.noteDao // Room data source
//            val keeper = NoteKeeperMemory() // Memory data source
            val service = RetrofitFactory.createRetrofit().create(NoteApiService::class.java)
            val apiRepository = NoteApiRepository(service)
            val noteRepository = NoteRepository(keeper, apiRepository)

            return NoteViewModel(noteRepository) as T
        }
        return super.create(modelClass)
    }
}