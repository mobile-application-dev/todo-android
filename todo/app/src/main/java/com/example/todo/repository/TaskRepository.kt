package com.example.todo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todo.model.Task

class TaskRepository {
    private var id:Int = 3
    private val allTasks: MutableLiveData<List<Task>> = MutableLiveData(
        mutableListOf(
            Task(1, "Task 1", false),
            Task(2, "Task 2", false),
            Task(3, "Task 3", false)
        )
    )

    fun insert(taskTitle: String, isCompleted: Boolean) {
        val currentTasks = allTasks.value?.toMutableList() ?: mutableListOf()
        currentTasks.add(Task(id++, taskTitle, isCompleted))
        allTasks.value = currentTasks
        Log.i("TaskRepository", "Task inserted: $taskTitle")
    }

    fun getAll(): LiveData<List<Task>> {
        Log.i("TaskRepository", "Task inserted: $allTasks")
        return allTasks
    }
}
