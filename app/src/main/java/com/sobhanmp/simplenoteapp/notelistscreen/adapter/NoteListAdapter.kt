package com.sobhanmp.simplenoteapp.notelistscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sobhanmp.domain.model.NoteModel
import com.sobhanmp.simplenoteapp.R
import com.sobhanmp.simplenoteapp.databinding.ItemNoteRecyclerBinding

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    private var noteList: List<NoteModel?>? = null
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    fun setNoteList(noteList: List<NoteModel?>?){
        this.noteList = noteList
        notifyDataSetChanged()
    }
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
        val list = listOf<Int>(
            R.color.light_blue,
            R.color.light_green,
            R.color.light_yellow,
            R.color.light_grey,
            R.color.white,
        )
        binding.item = noteList?.get(position)?.copy(backgroundColor = list.get(position%5))

        binding.root.setOnClickListener {
            onItemClickListener?.onClick(noteList?.get(position))
        }
    }

    interface OnItemClickListener{
        fun onClick(noteModel: NoteModel?)
    }
}