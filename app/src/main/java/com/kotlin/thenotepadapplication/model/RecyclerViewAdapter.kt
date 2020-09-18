package com.kotlin.thenotepadapplication.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.thenotepadapplication.R

class RecyclerViewAdapter(private val notesList: ArrayList<NotepadEntryPOJO>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.individual_note_layout, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindItems(notesList[position])
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(notepadEntryPOJO: NotepadEntryPOJO) {
            val titleTextView: TextView = itemView.findViewById(R.id.title_textView)
            val subtitleTextView: TextView = itemView.findViewById(R.id.subtitle_textView)
            val dateTextView: TextView = itemView.findViewById(R.id.date_textView)

            titleTextView.text = notepadEntryPOJO.title
            subtitleTextView.text = notepadEntryPOJO.subtitle
            dateTextView.text = notepadEntryPOJO.date
        }
    }
}