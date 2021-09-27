package com.example.android.notes.mappers

import com.example.android.notes.notes.Note
import com.example.android.notes.database.NoteEntity

interface DatabaseMapper {

    fun asNotesList(list: List<NoteEntity>): List<Note>
}