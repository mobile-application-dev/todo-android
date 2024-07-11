package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todo.adapter.NoteRecyclerViewAdapter
import com.example.todo.databinding.FragmentLandingBinding
import com.example.todo.model.Note
import com.example.todo.repository.NoteRepository
import com.example.todo.view.LoginViewModel
import com.example.todo.view.NoteViewModel
import java.time.LocalDate

class LandingFragment : Fragment() {
    private lateinit var binding: FragmentLandingBinding
    private lateinit var adapter: NoteRecyclerViewAdapter
    private lateinit var viewModel: NoteViewModel
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_landing, container, false)
        initViewModel()
        adapter = NoteRecyclerViewAdapter(listOf(), viewModel)
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
        setUpLogOutButton()
        return binding.root
    }

    private fun initViewModel() {
        viewModel = (activity as MainActivity).viewModel
        loginViewModel = (activity as MainActivity).viewModelLogin
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.notes.observe(viewLifecycleOwner) {
            adapter.setNotes(it)
        }
    }


    private fun setUpLogOutButton() {
        binding.logoutBtn.setOnClickListener {
            loginViewModel.logout()
            it.findNavController().navigate(R.id.action_landingFragment_to_loginFragment)
        }
    }
}