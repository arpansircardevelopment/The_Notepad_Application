package com.kotlin.thenotepadapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kotlin.thenotepadapplication.R
import com.kotlin.thenotepadapplication.model.NotepadEntryPOJO
import com.kotlin.thenotepadapplication.repository.DatabaseRepository
import java.text.SimpleDateFormat
import java.util.*

class AddEditFragment : Fragment(), View.OnClickListener {

    private lateinit var fragmentAddEditSaveButton: Button
    private lateinit var fragmentAddEditTitleTextView: TextView
    private lateinit var fragmentAddEditSubtitleTextView: TextView
    private lateinit var iMainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_add_edit, container, false)
        initializeWidgets(view)
        setOnClickListenerMethod()
        return view
    }

    /**Method to insert the entered text within the database.
     * The saving mechanism is facilitated using the NotepadEntryPOJO class
     * This argument is then sent off to the DatabaseRepository class for insertion into the database.*/
    private fun initiateSaveMethod(notepadEntryPOJO: NotepadEntryPOJO) {
        val databaseRepository = DatabaseRepository(context!!)
        databaseRepository.insertMethod(notepadEntryPOJO)
        iMainActivity.triggerOnBackPressed()

        databaseRepository.returnMutableLiveData().observe(
            this,
            { Toast.makeText(context, "Inserted in row: $it", Toast.LENGTH_SHORT).show() })
    }

    /**OnClick method that handles all the clicks performed in the current view.*/
    override fun onClick(view: View?) {
        if (view == fragmentAddEditSaveButton) {
            val titleString: String = fragmentAddEditTitleTextView.text.toString()
            val subtitleString: String = fragmentAddEditSubtitleTextView.text.toString()
            val dateString: String = getDateMethod()
            initiateSaveMethod(NotepadEntryPOJO(titleString, subtitleString, dateString))
        }
    }

    /**Method to get the date using the SimpleDateFormat class*/
    private fun getDateMethod(): String {
        return SimpleDateFormat("dd-MM-yy", Locale.US).format(Date())
    }

    /**Method to initializeWidgets present in the Fragment.*/
    private fun initializeWidgets(view: View) {
        fragmentAddEditSaveButton = view.findViewById(R.id.add_edit_fragment_save_button)
        fragmentAddEditTitleTextView = view.findViewById(R.id.add_edit_fragment_title_edit_text)
        fragmentAddEditSubtitleTextView =
            view.findViewById(R.id.add_edit_fragment_subtitle_edit_text)
        iMainActivity = activity as MainActivity
    }

    /**Method to intercept all the clicks performed in the current View*/
    private fun setOnClickListenerMethod() {
        fragmentAddEditSaveButton.setOnClickListener(this)
    }

    companion object {
        private const val TAG = "AddEditFragment"
    }

}