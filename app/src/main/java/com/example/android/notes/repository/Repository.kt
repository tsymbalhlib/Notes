package com.example.android.notes.repository

import androidx.lifecycle.LiveData
import com.example.android.notes.notes.Note

interface Repository {

    fun getNotesFromDb(): LiveData<List<Note>>

    suspend fun insertNoteToDb(note: Note)

    suspend fun updateNoteInDb(note: Note)

    suspend fun deleteNoteFromDbById(noteId: Long)

    suspend fun deleteNotesFromDb()

    suspend fun updateMarkedNotesInDb(noteId: Long, marked: Boolean)

    suspend fun deleteMarkedNotesFromDb()
}