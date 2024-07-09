package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.databinding.DataBindingUtil
import com.example.todo.adapter.NoteRecyclerViewAdapter
import com.example.todo.databinding.FragmentLandingBinding
import com.example.todo.model.Note
import com.example.todo.repository.NoteRepository
import java.time.LocalDate

class LandingFragment : Fragment() {
    private lateinit var binding: FragmentLandingBinding
    private lateinit var repository: NoteRepository
    private lateinit var adapter: NoteRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_landing, container, false)
        repository = NoteRepository()
        adapter = NoteRecyclerViewAdapter(repository.getNotes().value!!)
        binding.noteList.layoutManager = LinearLayoutManager(context)
        binding.noteList.adapter = adapter

        binding.addBox.visibility = View.GONE
        binding.floatingAddBtn.setOnClickListener {
            binding.addBox.visibility = View.VISIBLE
        }
        binding.addBtn.setOnClickListener {
            repository.insert(
                Note(
                    id = (repository.getNotes().value!!.size + 1).toLong(),
                    userId = 1,
                    title = binding.newText.text.toString(),
                    body = binding.newText.text.toString(),
                    date = LocalDate.now(),
                    longitude = 0.0,
                    latitude = 0.0
                )
            )
            adapter.notifyDataSetChanged()
            binding.addBox.visibility = View.GONE
        }
        return binding.root
    }


}