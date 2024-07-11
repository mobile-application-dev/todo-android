package com.example.todo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.NoteCardBinding
import com.example.todo.model.Note
import com.example.todo.R
import com.example.todo.utils.NetworkUtils
import com.example.todo.view.NoteViewModel

class NoteRecyclerViewAdapter(private var notes: List<Note>,
                              private var viewModel: NoteViewModel) :
    RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: NoteCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.editBtn.setOnClickListener{
                if (NetworkUtils.isNetworkAvailable(it.context)) {
                    sendNoteContent(it, R.id.action_landingFragment_to_editFragment)
                } else {
                    Toast.makeText(it.context, "No internet connection", Toast.LENGTH_SHORT).show()
                }
            }
            binding.deleteBtn.setOnClickListener {
                if (NetworkUtils.isNetworkAvailable(it.context)) {
                    loadNoteContent()
                    viewModel.delete()
                } else {
                    Toast.makeText(it.context, "No internet connection", Toast.LENGTH_SHORT).show()
                }
            }
        }

        private fun sendNoteContent(it: View, actionId: Int) {
            loadNoteContent()
            it.findNavController().navigate(actionId)
        }

        private fun loadNoteContent() {
            viewModel.id.value = binding.note?.id
            viewModel.title.value = binding.note?.title
            viewModel.body.value = binding.note?.body
            viewModel.date.value = binding.note?.date.toString()
            viewModel.longitude.value = binding.note?.longitude
            viewModel.latitude.value = binding.note?.latitude
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteCardBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.note = note
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}