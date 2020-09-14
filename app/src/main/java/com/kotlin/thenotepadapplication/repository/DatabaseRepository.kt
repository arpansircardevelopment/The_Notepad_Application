package com.kotlin.thenotepadapplication.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.kotlin.thenotepadapplication.model.NotepadContract
import com.kotlin.thenotepadapplication.model.NotepadEntryDatabaseHelper
import com.kotlin.thenotepadapplication.model.NotepadEntryPOJO

class DatabaseRepository(context: Context) {
    private val notepadEntryDatabaseHelper: NotepadEntryDatabaseHelper =
        NotepadEntryDatabaseHelper(context)

    fun insertMethod(title: String, subtitle: String, date: String): Long {
        val sqliteDatabase: SQLiteDatabase = notepadEntryDatabaseHelper.writableDatabase
        val insertValues: ContentValues = ContentValues().apply {
            put(NotepadContract.NotepadEntry.COLUMN_TITLE, title)
            put(NotepadContract.NotepadEntry.COLUMN_SUBTITLE, subtitle)
            put(NotepadContract.NotepadEntry.COLUMN_DATE, date)
        }

        return sqliteDatabase.insert(
            NotepadContract.NotepadEntry.TABLE_NAME,
            null,
            insertValues
        )
    }

    fun queryMethod(): ArrayList<NotepadEntryPOJO> {
        val sqLiteDatabase: SQLiteDatabase = notepadEntryDatabaseHelper.readableDatabase
        val sortOrder = "${BaseColumns._ID} DESC"

        val projection = arrayOf(
            NotepadContract.NotepadEntry.TABLE_NAME,
            NotepadContract.NotepadEntry.COLUMN_TITLE,
            NotepadContract.NotepadEntry.COLUMN_DATE
        )

        val cursor: Cursor = sqLiteDatabase.query(
            NotepadContract.NotepadEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        )

        return cursorParseMethod(cursor)
    }

    private fun cursorParseMethod(cursor: Cursor): ArrayList<NotepadEntryPOJO> {
        val databaseItems: ArrayList<NotepadEntryPOJO> = ArrayList()

        with(cursor) {
            while (cursor.moveToNext()) {
                val titleItem = getString(getColumnIndex(NotepadContract.NotepadEntry.COLUMN_TITLE))
                val subtitleItem =
                    getString(getColumnIndex(NotepadContract.NotepadEntry.COLUMN_SUBTITLE))
                val dateItem = getString(getColumnIndex(NotepadContract.NotepadEntry.COLUMN_DATE))
                databaseItems.add(NotepadEntryPOJO(titleItem, subtitleItem, dateItem))
            }
        }

        return databaseItems
    }
}