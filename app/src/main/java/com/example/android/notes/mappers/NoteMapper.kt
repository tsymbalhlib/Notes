package com.example.android.notes.mappers

import com.example.android.notes.database.NoteEntity
import com.example.android.notes.notes.Note

interface NoteMapper {

    fun asNoteEntity(note: Note): NoteEntity
}