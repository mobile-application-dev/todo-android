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

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        binding.backBtn.setOnClickListener() {
            it.findNavController().navigate(R.id.action_detailsFragment_to_landingFragment)
        }
        showDetailsNote()
        return binding.root
    }

    private fun showDetailsNote() {
        val note = requireArguments().getParcelable("note", Note::class.java)

        if (note != null) {
            binding.title.text = note.title
            binding.body.text = note.body
            binding.date.text = note.date.toString()
        }
    }
}