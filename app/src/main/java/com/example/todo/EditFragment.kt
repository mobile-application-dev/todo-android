package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.todo.databinding.FragmentEditBinding
import com.example.todo.view.NoteViewModel

class EditFragment : Fragment() {
    lateinit var binding: FragmentEditBinding
    private lateinit var viewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)
        viewModel = (activity as MainActivity).viewModel
        binding.viewModel = viewModel
        setUpSaveButton()
        setUpCancelButton()
        return binding.root
    }

    private fun setUpSaveButton() {
        binding.saveBtn.setOnClickListener {
            viewModel.update()
            it.findNavController().popBackStack()
        }
    }
    private fun setUpCancelButton() {
        binding.cancelBtn.setOnClickListener() {
            it.findNavController().popBackStack()
        }
    }
}