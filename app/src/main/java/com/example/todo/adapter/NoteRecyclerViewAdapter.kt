package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.NoteCardBinding
import com.example.todo.model.Note

class NoteRecyclerViewAdapter(private var notes: List<Note>) :
    RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(val binding: NoteCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
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