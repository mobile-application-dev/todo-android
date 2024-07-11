package com.example.todo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.todo.databinding.FragmentDetailsBinding
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
        setUpButtonToOpenInGoogleMaps()
        setUpDeleteButton()
        showDetailsNote()
        return binding.root
    }

    private fun setUpBackButton() {
        binding.backBtn.setOnClickListener() {
            viewModel.cancel()
            it.findNavController().popBackStack()
        }
    }
    private fun setUpDeleteButton() {
        binding.deleteBtn.setOnClickListener {
            viewModel.delete()
            it.findNavController().popBackStack()
        }
    }
    private fun showDetailsNote() {
        viewModel = (activity as MainActivity).viewModel
        binding.viewModel = viewModel
    }

    fun setUpButtonToOpenInGoogleMaps() {
        binding.localization.setOnClickListener() {
            val latitude = viewModel.latitude.value
            val longitude = viewModel.longitude.value
            val uri = "geo:0,0?q=$latitude,$longitude"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }
}