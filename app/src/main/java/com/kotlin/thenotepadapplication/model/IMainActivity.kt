package com.kotlin.thenotepadapplication.model

interface IMainActivity {
    fun triggerOnBackPressed()
    fun onNoteClicked(pos: Int)
}