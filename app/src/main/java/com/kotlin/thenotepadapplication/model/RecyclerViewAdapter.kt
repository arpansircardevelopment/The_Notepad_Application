package com.kotlin.thenotepadapplication.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.thenotepadapplication.R

class RecyclerViewAdapter(private val notesList: ArrayList<NotepadEntryPOJO>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    /**The onCreateViewHolder() is used for returning every single view present in the list.*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_note_layout, parent, false)
        return RecyclerViewHolder(view)
    }

    /**The onBindViewHolder() gets the data from the RecyclerViewHolder class and binds it to the list.*/
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindItems(notesList[position])
    }

    /**The getItemCount() method gets the size that our recyclerView is supposed to be.*/
    override fun getItemCount(): Int {
        return notesList.size
    }

    /**The RecyclerView.ViewHolder is tasked with holding each view present in the recyclerView*/
    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(notepadEntryPOJO: NotepadEntryPOJO) {
            val titleTextView: TextView = itemView.findViewById(R.id.title_textView)
            val subtitleTextView: TextView = itemView.findViewById(R.id.subtitle_textView)
            val dateTextView: TextView = itemView.findViewById(R.id.date_textView)

            titleTextView.text = notepadEntryPOJO.title
            subtitleTextView.text = countWords(notepadEntryPOJO.subtitle)
            dateTextView.text = notepadEntryPOJO.date
        }

        /**The countWords() method is used for setting the subtitle on the views in the RecyclerView.
         * If the character count for each subtitle exceeds 30 words, a triple-dot continuation sign is provided.
         * And this text is sent to be set in the RecyclerView.
         * In any other case, the original text is sent.*/
        private fun countWords(subtitle: String): String {
            val specifiedLength = 30

            return if (subtitle.length <= specifiedLength) {
                subtitle
            } else {
                var newSubtitle = ""

                for (i in 0 until specifiedLength) {
                    newSubtitle += subtitle[i]
                }

                "$newSubtitle..."
            }
        }
    }
}