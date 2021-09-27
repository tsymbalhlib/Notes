package com.example.android.notes.notes


import androidx.lifecycle.*
import com.example.android.notes.util.Event
import com.example.android.notes.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val notes = repository.getNotesFromDb()

    val empty: LiveData<Boolean> = Transformations.map(notes) { notesList ->
        notesList.isEmpty()
    }

    private val _navigateToNoteDetailFragment = MutableLiveData<Event<Note>>()
    val navigateToNoteDetailFragment: LiveData<Event<Note>> = _navigateToNoteDetailFragment

    fun onNoteClicked(note: Note) {
        _navigateToNoteDetailFragment.value = Event(note)
    }

    private val _navigateToAddNoteFragment = MutableLiveData<Event<Unit>>()
    val navigateToAddNoteFragment: LiveData<Event<Unit>> = _navigateToAddNoteFragment

    fun onAddNote() {
        _navigateToAddNoteFragment.value = Event(Unit)
    }

    fun onUpdateNoteMarked(note: Note, marked: Boolean) = viewModelScope.launch {
        repository.updateMarkedNotesInDb(note.noteId, marked)
    }

    fun onDeleteMarkedNotes() = viewModelScope.launch {
        repository.deleteMarkedNotesFromDb()
    }

    fun onDeleteAllNotes() = viewModelScope.launch {
        repository.deleteNotesFromDb()
    }
}