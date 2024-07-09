package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.todo.databinding.FragmentCreateNoteBinding
import com.example.todo.view.NoteViewModel

class CreateNoteFragment : Fragment() {
    private lateinit var binding: FragmentCreateNoteBinding
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_note, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.cancel.setOnClickListener {
            viewModel.cancel()
            it.findNavController().navigate(
                R.id.action_createNoteFragment_to_landingFragment
            )
        }

        binding.addNote.setOnClickListener {
            viewModel.insert()
            it.findNavController().navigate(
                R.id.action_createNoteFragment_to_landingFragment
            )
        }

    }
}
