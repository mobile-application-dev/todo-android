package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.todo.adapter.NoteRecyclerViewAdapter
import com.example.todo.databinding.FragmentLandingBinding
import com.example.todo.model.Note
import com.example.todo.repository.NoteRepository
import com.example.todo.view.NoteViewModel
import java.time.LocalDate

class LandingFragment : Fragment() {
    private lateinit var binding: FragmentLandingBinding
    private lateinit var repository: NoteRepository
    private lateinit var adapter: NoteRecyclerViewAdapter
    private lateinit var viewModel: NoteViewModel

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
            binding.newText.requestFocus()
        }
        binding.addBtn.setOnClickListener {
            binding.addBox.visibility = View.GONE
            it.findNavController().navigate(R.id.action_landingFragment_to_createNoteFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.notes.observe(viewLifecycleOwner) {
            adapter.setNotes(it)
        }
    }


}