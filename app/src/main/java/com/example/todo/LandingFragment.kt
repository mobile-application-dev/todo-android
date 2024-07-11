package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todo.adapter.NoteRecyclerViewAdapter
import com.example.todo.databinding.FragmentLandingBinding
import com.example.todo.ui.login.LoginViewModel
import com.example.todo.utils.NetworkUtils
import com.example.todo.view.NoteViewModel

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

        loginViewModel.loginSession.observe(viewLifecycleOwner) {
            if (it == "") {
                this.findNavController().navigate(R.id.action_landingFragment_to_loginFragment)
            }
        }

        setUpLogoutButton()
        binding.floatingAddBtn.setOnClickListener {
            if(NetworkUtils.isNetworkAvailable(requireContext())) {
                it.findNavController().navigate(R.id.action_landingFragment_to_createNoteFragment)
            } else {
                Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = (activity as MainActivity).viewModel
        loginViewModel = (activity as MainActivity).loginViewModel
        binding.viewModel = viewModel
        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            viewModel.getAllNotes()
        } else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpLogoutButton() {
        binding.logoutBtn.setOnClickListener {
            Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
            loginViewModel.logout()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            viewModel.notes.observe(viewLifecycleOwner) {
                adapter.setNotes(it)
            }
        } else {
            adapter.setNotes(viewModel.notes.value.orEmpty())
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
        }

    }


}
