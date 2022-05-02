package com.example.testtask.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.domain.model.Notes

class NoteAdapter (private  val notesList: List<Notes>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    interface ShortOnClickListener {
        fun ShortClick(noteId: Long, noteValue: String, noteName: String)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteItem.text = notesList[position].noteName
        holder.itemView.setOnClickListener {
            shortOnClickListener?.ShortClick(
                notesList[position].noteId,
                notesList[position].noteValue,
                notesList[position].noteName
                )
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val noteItem = itemView.findViewById<TextView>(R.id.tv_note_item_title)

    }

}