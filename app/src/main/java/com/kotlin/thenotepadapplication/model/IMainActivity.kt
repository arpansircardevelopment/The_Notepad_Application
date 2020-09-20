package com.kotlin.thenotepadapplication.model

/**Interface to facilitate activity-to-fragment communication.*/
interface IMainActivity {
    /*The triggerOnBackPressed method is triggered when the back button is pressed.
    * This, in turn, triggers the onBackPressed() method present in the MainActivity.*/
    fun triggerOnBackPressed()

    /*The onNoteClicked() method is used purely for the purpose of getting the adapter position for the view that was clicked.*/
    fun onNoteClicked(pos: Int)
}