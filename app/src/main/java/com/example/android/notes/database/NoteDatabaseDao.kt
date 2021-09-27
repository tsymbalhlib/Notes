package com.example.android.notes.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDatabaseDao {

    //Select all notes from the notes table.
    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<NoteEntity>>

    //Insert a note in the database. If the note already exists, replace it.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    //Update a note.
    @Update
    suspend fun updateNote(note: NoteEntity)

    //Delete a note by id.
    @Query("DELETE FROM Notes WHERE noteId = :noteId")
    suspend fun deleteNoteById(noteId: Long)

    //Delete all notes.
    @Query("DELETE FROM Notes")
    suspend fun deleteNotes()

    //Update the marked status of a note.
    @Query("UPDATE Notes SET marked = :marked WHERE noteId = :noteId")
    suspend fun updateMarkedNotes(noteId: Long, marked: Boolean)

    //Delete all marked notes from the table.
    @Query("DELETE FROM Notes WHERE marked = 1")
    suspend fun deleteMarkedNotes()

}