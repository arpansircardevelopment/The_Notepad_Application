package com.kotlin.thenotepadapplication.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotepadEntryDatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    NotepadContract.DATABASE_NAME,
    null,
    NotepadContract.DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(NotepadContract.SQLITE_CREATE_ENTRY)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}