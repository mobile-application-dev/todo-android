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
import com.example.todo.view.NoteViewModel

class LandingFragment : Fragment() {
    private lateinit var binding: FragmentLandingBinding
    private lateinit var adapter: NoteRecyclerViewAdapter
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_landing, container, false)
        initViewModel()
        adapter = NoteRecyclerViewAdapter(listOf(), viewModel)
        binding.noteList.layoutManager = LinearLayoutManager(context)
        binding.noteList.adapter = adapter

        binding.floatingAddBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_landingFragment_to_createNoteFragment)
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = (activity as MainActivity).viewModel
        binding.viewModel = viewModel
        viewModel.getAllNotes()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.notes.observe(viewLifecycleOwner) {
            adapter.setNotes(it)
        }
    }


}
