package com.example.android.notes.notes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    var title: String = "",
    var description: String = "",
    var marked: Boolean = false,
    var noteId: Long = 0L
) : Parcelable