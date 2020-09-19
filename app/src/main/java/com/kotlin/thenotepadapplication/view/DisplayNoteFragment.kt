package com.kotlin.thenotepadapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kotlin.thenotepadapplication.R

class DisplayNoteFragment : Fragment(), View.OnClickListener {

    private lateinit var subtitle: String
    private lateinit var date: String

    private lateinit var fragmentDisplayNoteDateTextView: TextView
    private lateinit var fragmentDisplayNoteSubtitleTextView: TextView
    private lateinit var fragmentDisplayNoteEditButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subtitle = this.arguments?.getString("subtitle").toString()
        date = this.arguments?.getString("date").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_display_note, container, false)
        initializeWidgets(view)
        setWidgetValues()
        setOnClickListener()
        return view
    }

    private fun initializeWidgets(view: View) {
        fragmentDisplayNoteDateTextView =
            view.findViewById(R.id.display_note_fragment_date_text_view)
        fragmentDisplayNoteSubtitleTextView =
            view.findViewById(R.id.display_note_fragment_subtitle_text_view)
        fragmentDisplayNoteEditButton = view.findViewById(R.id.display_note_fragment_edit_button)
    }

    private fun setWidgetValues() {
        fragmentDisplayNoteDateTextView.text = date
        fragmentDisplayNoteSubtitleTextView.text = subtitle
    }

    private fun setOnClickListener() {
        fragmentDisplayNoteEditButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {

    }

}