package com.sobhanmp.simplenoteapp.notelistscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sobhanmp.domain.model.NoteModel
import com.sobhanmp.simplenoteapp.R
import com.sobhanmp.simplenoteapp.databinding.ItemNoteRecyclerBinding

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    var noteList: List<NoteModel?>? = null

    class ViewHolder(val binding: ItemNoteRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_note_recycler,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = noteList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding

        binding.item = noteList?.get(position)
    }
}