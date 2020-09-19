package com.kotlin.thenotepadapplication.model

import android.provider.BaseColumns

object NotepadContract {

    /**Constant SQLite query for creating a new table.*/
    const val SQLITE_CREATE_ENTRY = "CREATE TABLE ${NotepadEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "${NotepadEntry.COLUMN_TITLE} TEXT, " +
            "${NotepadEntry.COLUMN_SUBTITLE} TEXT, " +
            "${NotepadEntry.COLUMN_DATE} TEXT)"

    /**Constants defined for database name and the database version.*/
    const val DATABASE_NAME: String = "notepad.db"
    const val DATABASE_VERSION: Int = 1

    /**Individual NotepadEntry constants are grouped together in a single anonymous object.*/
    object NotepadEntry : BaseColumns {
        const val TABLE_NAME = "entries"
        const val COLUMN_TITLE = "title"
        const val COLUMN_SUBTITLE = "subtitle"
        const val COLUMN_DATE = "date"
    }
}