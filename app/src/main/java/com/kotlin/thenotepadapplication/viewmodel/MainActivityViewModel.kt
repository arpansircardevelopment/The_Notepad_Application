package com.kotlin.thenotepadapplication.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.kotlin.thenotepadapplication.model.NotepadEntryPOJO
import com.kotlin.thenotepadapplication.repository.DatabaseRepository

class MainActivityViewModel(context: Context) : ViewModel() {
    private val databaseRepository: DatabaseRepository = DatabaseRepository(context)

    private fun insertDataIntoDatabase(notepadEntryPOJO: NotepadEntryPOJO): Long {
        return databaseRepository.insertMethod(notepadEntryPOJO)
    }

    private fun queryDataFromDatabase(): ArrayList<NotepadEntryPOJO> {
        return databaseRepository.queryMethod()
    }
}