package com.kotlin.thenotepadapplication.model

/**A Plain Old Java Object created using Kotlin data classes.
 * The NotepadEntryPOJO class will be used whenever I want to pass data from or to the database.*/
data class NotepadEntryPOJO(val title: String, val subtitle: String, val date: String)