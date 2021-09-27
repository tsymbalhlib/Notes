package com.example.android.notes.mappers

import com.example.android.notes.database.NoteEntity
import com.example.android.notes.notes.Note
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseMapperImpl @Inject constructor() : DatabaseMapper {

    override fun asNotesList(list: List<NoteEntity>): List<Note> {
        return list.map {
            Note(
                title = it.title,
                description = it.description,
                marked = it.marked,
                noteId = it.noteId
            )
        }
    }
}