package com.kwabenaberko.finito.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kwabenaberko.finito.R
import com.kwabenaberko.finito.databinding.NoteListItemBinding
import com.kwabenaberko.finito.viewmodel.dto.NoteListItem

class NoteListAdapter(val listener: NoteListItemSelectedListener) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>(){

    private val noteList = arrayListOf<NoteListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val binding = DataBindingUtil.inflate<NoteListItemBinding>(
               LayoutInflater.from(parent.context), R.layout.note_list_item, parent, false
       )
        return NoteViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return noteList.size
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    fun setNoteList(list: List<NoteListItem>){
        noteList.clear() //Inefficient
        noteList.addAll(list)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder (private val binding: NoteListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note: NoteListItem){
            binding.note = note
            binding.root.setOnClickListener { listener.onEdit(note) }
            binding.deleteBtn.setOnClickListener { listener.onDelete(note) }
            binding.executePendingBindings()
        }
    }
}