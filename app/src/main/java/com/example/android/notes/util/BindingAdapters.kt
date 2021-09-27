package com.example.android.notes.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.notes.notes.Note
import com.example.android.notes.notes.NotesAdapter

@BindingAdapter("items")
fun setItems(listView: RecyclerView, items: List<Note>?) {
    items?.let { list ->
        (listView.adapter as NotesAdapter).submitList(list)
    }
}