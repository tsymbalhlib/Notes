package com.example.android.notes.notedetail

import androidx.lifecycle.*
import com.example.android.notes.util.Event
import com.example.android.notes.R
import com.example.android.notes.notes.Note
import com.example.android.notes.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val note = savedStateHandle.get<Note>("note")

    var noteTitle = savedStateHandle.get<String>("noteTitle") ?: note?.title ?: ""
        set(value) {
            field = value
            savedStateHandle.set("noteTitle", value)
        }

    var noteDescription = savedStateHandle.get<String>("noteDescription") ?: note?.description ?: ""
        set(value) {
            field = value
            savedStateHandle.set("noteDescription", value)
        }

    private val _showSnackBarEventValue = MutableLiveData<Event<Int>>()
    val showSnackBarEventValue: LiveData<Event<Int>> = _showSnackBarEventValue

    private val _navigateToNotesFragment = MutableLiveData<Event<Unit>>()
    val navigateToNotesFragment: LiveData<Event<Unit>> = _navigateToNotesFragment

    fun onUpdateNoteClicked() {
        note?.let { note ->
            if (noteTitle.isEmpty() || noteDescription.isEmpty()) {
                _showSnackBarEventValue.value = Event(R.string.note_can_not_be_empty)
                return
            }
            onUpdateNote(Note(noteTitle, noteDescription, note.marked, note.noteId))
        }
    }

    private fun onUpdateNote(note: Note) = viewModelScope.launch {
        repository.updateNoteInDb(note)
        _navigateToNotesFragment.value = Event(Unit)
    }

    fun onDeleteNote() = viewModelScope.launch {
        note?.let { note ->
            repository.deleteNoteFromDbById(note.noteId)
            _navigateToNotesFragment.value = Event(Unit)
        }
    }
}