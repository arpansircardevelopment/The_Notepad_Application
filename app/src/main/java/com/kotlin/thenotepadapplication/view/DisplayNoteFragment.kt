package com.kotlin.thenotepadapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.kotlin.thenotepadapplication.R

class DisplayNoteFragment : Fragment() {

    private lateinit var title: String
    private lateinit var subtitle: String
    private lateinit var date: String

    private lateinit var fragmentDisplayNoteToolbar: Toolbar
    private lateinit var fragmentDisplayNoteDateTextView: TextView
    private lateinit var fragmentDisplayNoteSubtitleTextView: TextView
    private lateinit var fragmentDisplayNoteEditButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = this.arguments?.getString("title").toString()
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
        return view
    }

    /**Method to initialize all the widgets present in the Fragment.*/
    private fun initializeWidgets(view: View) {
        fragmentDisplayNoteDateTextView =
            view.findViewById(R.id.display_note_fragment_date_text_view)
        fragmentDisplayNoteSubtitleTextView =
            view.findViewById(R.id.display_note_fragment_subtitle_text_view)
        fragmentDisplayNoteToolbar = view.findViewById(R.id.display_note_fragment_toolbar_layout)
    }

    /**Method to initialize the initial values of all the widgets */
    private fun setWidgetValues() {
        fragmentDisplayNoteToolbar.title = title
        fragmentDisplayNoteDateTextView.text = date
        fragmentDisplayNoteSubtitleTextView.text = subtitle
    }

}