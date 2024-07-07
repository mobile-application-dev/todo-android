package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.databinding.DataBindingUtil
import com.example.todo.databinding.FragmentLandingBinding
import com.example.todo.repository.TaskRepository

class LandingFragment : Fragment() {
    private lateinit var binding: FragmentLandingBinding
    private lateinit var taskList: TaskRepository
    private lateinit var taskAdapter: TaskListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_landing, container, false)
        taskList = TaskRepository()
        taskAdapter = TaskListAdapter(taskList.getAll())
        binding.taskList.layoutManager = LinearLayoutManager(context)
        binding.taskList.adapter = taskAdapter

        binding.addBox.visibility = View.GONE
        binding.floatingAddBtn.setOnClickListener {
            binding.addBox.visibility = View.VISIBLE
        }
        binding.addBtn.setOnClickListener {
            taskList.insert(binding.newText.text.toString(), false)
            taskAdapter.notifyDataSetChanged()
            binding.addBox.visibility = View.GONE
        }
        return binding.root
    }


}