package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.model.Task

class TaskListAdapter(private val taskList: LiveData<List<Task>>): RecyclerView.Adapter<TaskListAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskCheckBox: CheckBox = view.findViewById(R.id.task)
        init {
            taskCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    Toast.makeText(view.context, "Task Completed", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(view.context, "Task Uncompleted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_task, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return taskList.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList.value!![position]
        holder.taskCheckBox.text = task.title
        holder.taskCheckBox.isChecked = task.isCompleted
    }
}