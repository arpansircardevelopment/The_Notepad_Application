package com.kotlin.thenotepadapplication.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kotlin.thenotepadapplication.model.NotepadContract
import com.kotlin.thenotepadapplication.model.NotepadEntryDatabaseHelper

class DatabaseRepository(context: Context) {
    private val notepadEntryDatabaseHelper: NotepadEntryDatabaseHelper =
        NotepadEntryDatabaseHelper(context)

    fun insertMethod(title: String, subtitle: String, date: String): Long {
        val sqliteDatabaseHelper: SQLiteDatabase = notepadEntryDatabaseHelper.writableDatabase
        val insertValues: ContentValues = ContentValues().apply {
            put(NotepadContract.NotepadEntry.COLUMN_TITLE, title)
            put(NotepadContract.NotepadEntry.COLUMN_SUBTITLE, subtitle)
            put(NotepadContract.NotepadEntry.COLUMN_DATE, date)
        }

        return sqliteDatabaseHelper.insert(
            NotepadContract.NotepadEntry.TABLE_NAME,
            null,
            insertValues
        )
    }

    fun queryMethod(){

    }
}