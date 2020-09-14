package com.kotlin.thenotepadapplication.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**The DatabaseHelper class used for initializing for performing initial database operations*/
class NotepadEntryDatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    NotepadContract.DATABASE_NAME,
    null,
    NotepadContract.DATABASE_VERSION
) {

    /**The onCreate method is used for initially creating the database.*/
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(NotepadContract.SQLITE_CREATE_ENTRY)
    }

    /**The onUpgrade is used when we want to upgrade database.
     * Usually upgrade refers to when we add more columns or remove them and other such operations.*/
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}