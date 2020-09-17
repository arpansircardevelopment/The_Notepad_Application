package com.kotlin.thenotepadapplication.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.lifecycle.MutableLiveData
import com.kotlin.thenotepadapplication.model.NotepadContract
import com.kotlin.thenotepadapplication.model.NotepadEntryDatabaseHelper
import com.kotlin.thenotepadapplication.model.NotepadEntryPOJO

/**The DatabaseRepository class is tasked with managing all the Database operations.
 * The repository class isn't usually included with the MVVM architecture, but it recommended for best practices.
 * The ViewModel will be communicating with this Repository class to perform all the operations.*/
class DatabaseRepository(context: Context) {
    private val notepadEntryDatabaseHelper: NotepadEntryDatabaseHelper = NotepadEntryDatabaseHelper(context)
    private val rowMutableLiveData: MutableLiveData<Long> = MutableLiveData()

    /**Method used for performing inserting values into the database */
    fun insertMethod(notepadEntryPOJO: NotepadEntryPOJO) {
        val sqliteDatabase: SQLiteDatabase = notepadEntryDatabaseHelper.writableDatabase
        val insertValues: ContentValues = ContentValues().apply {
            put(NotepadContract.NotepadEntry.COLUMN_TITLE, notepadEntryPOJO.title)
            put(NotepadContract.NotepadEntry.COLUMN_SUBTITLE, notepadEntryPOJO.subtitle)
            put(NotepadContract.NotepadEntry.COLUMN_DATE, notepadEntryPOJO.date)
        }

        /*Insert queries usually return a long value signifying the row in which the value has been inserted.
        * The calling method for the insertMethod() will display this returned value as a Toast.*/
        val rowID: Long = sqliteDatabase.insert(
            NotepadContract.NotepadEntry.TABLE_NAME,
            null,
            insertValues
        )

        rowMutableLiveData.postValue(rowID)
    }

    fun returnMutableLiveData(): MutableLiveData<Long>{
        return rowMutableLiveData
    }

    /**Method for querying all the details present in the database.
     * The details are extracted in a cursor format that is sent to the cursorParseMethod() for extraction.
     * The cursorParseMethod() then returns an ArrayList of type NotepadEntryPOJO to this current method.
     * This method then, promptly, returns the same ArrayList.*/
    fun queryMethod(): ArrayList<NotepadEntryPOJO> {
        val sqLiteDatabase: SQLiteDatabase = notepadEntryDatabaseHelper.readableDatabase
        val sortOrder = "${BaseColumns._ID} DESC"

        /*The projection specifies the exact columns from which we want to extract data from the database.
        * In this case, the column ID is being omitted as it isn't quite usable in the Views.*/
        val projection = arrayOf(
            NotepadContract.NotepadEntry.TABLE_NAME,
            NotepadContract.NotepadEntry.COLUMN_TITLE,
            NotepadContract.NotepadEntry.COLUMN_DATE
        )

        /*The cursor object extract only the columns as specified in the projection array.
        * In this case, it extracts all the columns with the exception of the ID column.
        * The ID column is being used only for sorting the values into a descending order.*/
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

    /**The cursorParseMethod() takes an argument of type Cursor.
     * It queries this cursor object and extract all the files into a separate ArrayList.
     * This ArrayList is sent back to the query method.*/
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