# The Notepad Application
A simple notepad application that I built after learning SQLite and database operations in Android. I originally planned to build the app using MVVM, but there were various roadblocks that prevented me from doing so, particularly with the ViewModel. The app is built using Kotlin.

There are 3 major layouts: 
1. The MainActivity activity - Houses the fragments and other components such as the Toolbar, RecyclerView, and the Floating Buttons. 
2. The AddEditFragment fragment - Used for adding and editing notes (earlier planned to create the editing function, but was unable to.)
3. The DisplayFragment fragment - Used for displaying a particular note as well as the date on which it was created.

Functionality - Quite simply, this is what the app is supposed to do:

1. The user starts the app in the MainActivity. The layout is empty save for two floating action buttons (FAB), a toolbar displaying the app name, and an empty recyclerView.

2. Upon pressing the Add FAB, the AddEditFragment appears and the MainActivity turns invisible. The Toolbar displays the term "New Note". Once the user has entered the title and subtitle text, he can press the Save button. This brings him back to the MainActivity and the new note appears in the RecyclerView.

3. Now, when the user clicks this (or any other previously created note), he is directed to a new layout displaying the note details. The toolbar contains the title for the note. Beneath it lies the date on which the note was created. Finally, beneath it is a CardView housing the rest of the note contents, or quite simply, the subtitle.

4. Upon pressing the back button, the user is directed back to the MainActivity. This leaves the user with just one functionality to explore, the delete FAB. Since I only created this app to test the delete functionality in SQLite, clicking this button doesn't delete a particular note, it deletes all of them. Ever last note is eviserated straight out of the database with a Toast message stating "All Notes Deleted".

Roadblocks:
1. My primary roadblock was implementing MVVM for passing the Context object from the MainActivity to the NotepadEntryDatabaseHelper.kt class. Normally, it isn't recommended to pass context object into ViewModels. A quick Ecosia search also showed why I shouldn't use the AndroidViewModel class. Therefore, I decided to leave the project as it was. I had already accomplished my primary purpose of building this project and decided to take the W.

2. Apart from this, I faced some problems while creating the Edit Note functionality. Originally, the app was supposed to allow the user to edit their newly created notes as well. However, since I created the app completely using a single activity with different fragments for other layouts, several bugs kept popping up. So the decided to remove it.
