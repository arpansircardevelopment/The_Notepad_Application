package com.kotlin.thenotepadapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kotlin.thenotepadapplication.R
import com.kotlin.thenotepadapplication.model.IMainActivity

class MainActivity : AppCompatActivity(), View.OnClickListener, IMainActivity {

    private lateinit var activityMainToolbar: Toolbar
    private lateinit var activityMainRecyclerView: RecyclerView
    private lateinit var activityMainConstraintLayout: ConstraintLayout
    private lateinit var activityMainFragmentConstraintLayout: ConstraintLayout
    private lateinit var activityMainFloatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeWidgets()
        initializeToolbar(R.string.app_name)
        setOnClickListenerMethod()
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

    /**Method to initialize the widgets present in the View*/
    private fun initializeWidgets() {
        activityMainToolbar = findViewById(R.id.activity_main_toolbar_layout)
        activityMainRecyclerView = findViewById(R.id.activity_main_recycler_view)
        activityMainFloatingActionButton = findViewById(R.id.activity_main_floating_action_button)
        activityMainConstraintLayout = findViewById(R.id.activity_main_constraint_layout)
        activityMainFragmentConstraintLayout =
            findViewById(R.id.activity_main_fragment_constraint_layout)
    }

    /**Method to initialize the Activity toolbar*/
    private fun initializeToolbar(toolbarTitle: Int) {
        setSupportActionBar(activityMainToolbar)
        supportActionBar!!.setTitle(toolbarTitle)
    }

    /**Method to set the onClickListeners for all the required views in the application.*/
    private fun setOnClickListenerMethod() {
        activityMainFloatingActionButton.setOnClickListener(this)
    }

    /**Method to intercept all the clicks performed in the current View*/
    override fun onClick(view: View?) {
        if (view == activityMainFloatingActionButton) {
            val addEditFragment = AddEditFragment()
            val titleString: Int = R.string.new_note_string
            initializeFragmentTransactions(addEditFragment, titleString)
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
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun triggerOnBackPressed() {
        onBackPressed()
    }
}