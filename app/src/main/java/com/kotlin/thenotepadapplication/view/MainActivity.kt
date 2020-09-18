package com.kotlin.thenotepadapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kotlin.thenotepadapplication.R
import com.kotlin.thenotepadapplication.model.IMainActivity
import com.kotlin.thenotepadapplication.model.NotepadEntryPOJO
import com.kotlin.thenotepadapplication.model.RecyclerViewAdapter
import com.kotlin.thenotepadapplication.repository.DatabaseRepository

class MainActivity : AppCompatActivity(), View.OnClickListener, IMainActivity {

    private lateinit var activityMainToolbar: Toolbar
    private lateinit var activityMainRecyclerView: RecyclerView
    private lateinit var activityMainConstraintLayout: ConstraintLayout
    private lateinit var activityMainFragmentConstraintLayout: ConstraintLayout
    private lateinit var activityMainFloatingActionButton: FloatingActionButton
    private lateinit var activityMainDeleteFloatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeWidgets()
        initializeToolbar(R.string.app_name)
        initializeRecyclerView()
        setOnClickListenerMethod()
        queryMethod()
    }

    /**Governing method that overlooks all fragment transactions taking place
     * First, it makes the current ConstraintLayout invisible while bringing the ConstraintLayout designated for the fragments into view.
     * Next, depending on the function to be performed, it then segregates the work functions in:
     * 1. the initializeToolbar() method
     * 2. the performFragmentTransactionMethod() method*/
    private fun initializeFragmentTransactions(fragment: Fragment, toolbarTitle: Int) {
        activityMainConstraintLayout.visibility = View.INVISIBLE
        activityMainFragmentConstraintLayout.visibility = View.VISIBLE
        initializeToolbar(toolbarTitle)
        performFragmentTransactionMethod(fragment)
    }

    /**The performFragmentTransactionMethod() is charged simply with changing the current fragment that's present.
     * It takes in the required fragment to be attached as an argument and then changes to it.*/
    private fun performFragmentTransactionMethod(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.activity_main_fragment_constraint_layout, fragment)
        fragmentTransaction.commit()
    }

    /**The queryMethod() queries all the entries present in the database, only to be displayed in the RecyclerView*/
    private fun queryMethod() {
        val databaseRepository = DatabaseRepository(applicationContext)
        val arrayList: ArrayList<NotepadEntryPOJO> = databaseRepository.queryMethod()
        val adapter = RecyclerViewAdapter(arrayList)
        activityMainRecyclerView.adapter = adapter
    }

    /**Method to initialize the widgets present in the View*/
    private fun initializeWidgets() {
        activityMainToolbar = findViewById(R.id.activity_main_toolbar_layout)
        activityMainConstraintLayout = findViewById(R.id.activity_main_constraint_layout)
        activityMainFloatingActionButton = findViewById(R.id.activity_main_floating_action_button)
        activityMainFragmentConstraintLayout =
            findViewById(R.id.activity_main_fragment_constraint_layout)
        activityMainDeleteFloatingActionButton =
            findViewById(R.id.activity_main_delete_floating_action_button)
    }

    /**A separate method to initialize the RecyclerView and the other aspects associated with it*/
    private fun initializeRecyclerView() {
        activityMainRecyclerView = findViewById(R.id.activity_main_recycler_view)
        activityMainRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    /**Method to delete all the data present in the database*/
    private fun deleteAllNotesMethod() {
        val databaseRepository = DatabaseRepository(applicationContext)
        databaseRepository.deleteMethod()
        queryMethod()
        Toast.makeText(applicationContext, "All Notes Deleted", Toast.LENGTH_SHORT).show()
    }

    /**Method to initialize the Activity toolbar*/
    private fun initializeToolbar(toolbarTitle: Int) {
        setSupportActionBar(activityMainToolbar)
        supportActionBar!!.setTitle(toolbarTitle)
    }

    /**Method to set the onClickListeners for all the required views in the application.*/
    private fun setOnClickListenerMethod() {
        activityMainFloatingActionButton.setOnClickListener(this)
        activityMainDeleteFloatingActionButton.setOnClickListener(this)
    }

    /**Method to intercept all the clicks performed in the current View*/
    override fun onClick(view: View?) {

        /*When the user clicks the activityMainFloatingActionButton, an instance of the AddEditFragment is created.
        * This instance allows us to start the AddEditFragment for a new note to be created.
        * Apart from this, the title string for the new note is also created.
        * This is placed and displayed in the toolbar when the new note is being created. */
        if (view == activityMainFloatingActionButton) {
            val addEditFragment = AddEditFragment()
            val titleString: Int = R.string.new_note_string
            initializeFragmentTransactions(addEditFragment, titleString)
        }

        /*If the user clicks on the activityMainDeleteFloatingActionButton, the deleteAllNotesMethod().
        * This note promptly goes on to delete all the notes present in the recyclerView.*/
        if (view == activityMainDeleteFloatingActionButton) {
            deleteAllNotesMethod()
        }
    }

    /**Function that checks what to when the back button is pressed.
     * If any fragment is active, it simply means that the activityMainFragmentConstraintLayout is invisible.
     * Therefore, pressing the back button should bring the user back to the home page of the application.
     * In the other case, i.e., the user is present on the main page, the app should exit. */
    override fun onBackPressed() {
        if (activityMainConstraintLayout.visibility == View.INVISIBLE) {
            activityMainFragmentConstraintLayout.visibility = View.INVISIBLE
            activityMainConstraintLayout.visibility = View.VISIBLE
            initializeToolbar(R.string.app_name)
            queryMethod()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    /**Interface method to facilitate fragment-to-activity communication*/
    override fun triggerOnBackPressed() {
        onBackPressed()
    }
}