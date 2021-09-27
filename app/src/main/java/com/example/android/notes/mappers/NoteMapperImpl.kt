package com.example.android.notes.mappers

import com.example.android.notes.database.NoteEntity
import com.example.android.notes.notes.Note
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteMapperImpl @Inject constructor() : NoteMapper {

    override fun asNoteEntity(note: Note): NoteEntity {
        return NoteEntity(
            title = note.title,
            description = note.description,
            marked = note.marked,
            noteId = note.noteId
        )
    }
}