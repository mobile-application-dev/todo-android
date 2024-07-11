package com.example.todo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todo.model.Note
import com.example.todo.repository.NoteKeeper

@Dao
interface NoteDao : NoteKeeper {
    @Insert
    override suspend fun insert(note: Note)

    @Update
    override suspend fun update(note: Note)

    @Delete
    override suspend fun delete(note: Note)

    @Query("SELECT * FROM notes Order By id DESC")
    override fun getNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertAll(notes: List<Note>)
}