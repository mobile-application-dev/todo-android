package com.example.todo.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.MainActivity
import com.example.todo.databinding.NoteCardBinding
import com.example.todo.model.Note
import com.example.todo.R
import com.example.todo.view.NoteViewModel

class NoteRecyclerViewAdapter(private var notes: List<Note>) :
    RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(val binding: NoteCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.cardView.setOnClickListener {
                val bundle = bundleOf("note" to binding.note)
                it.findNavController().navigate(R.id.action_landingFragment_to_detailsFragment, bundle)
            }
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