package com.example.android.notes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "marked")
    var marked: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L
)