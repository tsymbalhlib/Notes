package com.example.android.notes.addnote

import androidx.lifecycle.*
import com.example.android.notes.util.Event
import com.example.android.notes.R
import com.example.android.notes.notes.Note
import com.example.android.notes.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var noteTitle = savedStateHandle.get<String>("noteTitle") ?: ""
        set(value) {
            field = value
            savedStateHandle.set("noteTitle", value)
        }

    var noteDescription = savedStateHandle.get<String>("noteDescription") ?: ""
        set(value) {
            field = value
            savedStateHandle.set("noteDescription", value)
        }

    private val _showSnackBarEventValue = MutableLiveData<Event<Int>>()
    val showSnackBarEventValue: LiveData<Event<Int>> = _showSnackBarEventValue

    private val _navigateToNotesFragment = MutableLiveData<Event<Unit>>()
    val navigateToNotesFragment: LiveData<Event<Unit>> = _navigateToNotesFragment

    fun onSaveNoteClicked() {
        if (noteTitle.isEmpty() || noteDescription.isEmpty()) {
            _showSnackBarEventValue.value = Event(R.string.note_can_not_be_empty)
            return
        }
        onSaveNote(Note(noteTitle, noteDescription, false))
    }

    private fun onSaveNote(note: Note) = viewModelScope.launch {
        repository.insertNoteToDb(note)
        _navigateToNotesFragment.value = Event(Unit)
    }
}