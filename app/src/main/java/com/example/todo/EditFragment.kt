package com.example.todo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.todo.databinding.FragmentEditBinding
import com.example.todo.model.Note
import java.time.LocalDate

class EditFragment : Fragment() {
    lateinit var binding: FragmentEditBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)
        binding.saveBtn.setOnClickListener {
            val note = requireArguments().getParcelable("note", Note::class.java)
            note?.title = binding.title.text.toString()
            note?.body = binding.body.text.toString()
            note?.date = LocalDate.parse(binding.date.text.toString())
            Log.d("EditFragment", "Note: $note")
        }
        loadData()
        return binding.root
    }
    private fun loadData() {
        val note = requireArguments().getParcelable("note", Note::class.java)

        if (note != null) {
            binding.title.setText(note.title)
            binding.body.setText(note.body)
            binding.date.setText(note.date.toString())
        }
    }
}