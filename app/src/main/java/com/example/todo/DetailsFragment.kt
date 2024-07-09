package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.todo.databinding.FragmentDetailsBinding
import com.example.todo.model.Note
import com.example.todo.view.NoteViewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        setUpBackButton()
        setUpDeleteButton()
        showDetailsNote()
        return binding.root
    }

    private fun setUpBackButton() {
        binding.backBtn.setOnClickListener() {
            it.findNavController().navigate(R.id.action_detailsFragment_to_landingFragment)
        }
    }
    private fun setUpDeleteButton() {
        binding.deleteBtn.setOnClickListener {
            viewModel = (activity as MainActivity).viewModel
            viewModel.delete(binding.note!!)
            it.findNavController().navigate(R.id.action_detailsFragment_to_landingFragment)
        }
    }
    private fun showDetailsNote() {
        val note = requireArguments().getParcelable("note", Note::class.java)
        if (note != null) {
            binding.note = note
        }
    }
}