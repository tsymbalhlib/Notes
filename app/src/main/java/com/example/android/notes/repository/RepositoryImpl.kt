package com.example.android.notes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.android.notes.database.NoteDatabaseDao
import com.example.android.notes.mappers.DatabaseMapper
import com.example.android.notes.mappers.NoteMapper
import com.example.android.notes.notes.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val noteDatabaseDao: NoteDatabaseDao,
    private val databaseMapper: DatabaseMapper,
    private val noteMapper: NoteMapper
) : Repository {

    override fun getNotesFromDb(): LiveData<List<Note>> {
        return noteDatabaseDao.getNotes().map {
            databaseMapper.asNotesList(it)
        }
    }

    override suspend fun insertNoteToDb(note: Note) = withContext(Dispatchers.IO) {
        noteDatabaseDao.insertNote(noteMapper.asNoteEntity(note))
    }

    override suspend fun updateNoteInDb(note: Note) = withContext(Dispatchers.IO) {
        noteDatabaseDao.updateNote(noteMapper.asNoteEntity(note))
    }

    override suspend fun deleteNoteFromDbById(noteId: Long) = withContext(Dispatchers.IO) {
        noteDatabaseDao.deleteNoteById(noteId)
    }

    override suspend fun deleteNotesFromDb() = withContext(Dispatchers.IO) {
        noteDatabaseDao.deleteNotes()
    }

    override suspend fun updateMarkedNotesInDb(noteId: Long, marked: Boolean) =
        withContext(Dispatchers.IO) {
            noteDatabaseDao.updateMarkedNotes(noteId, marked)
        }

    override suspend fun deleteMarkedNotesFromDb() = withContext(Dispatchers.IO) {
        noteDatabaseDao.deleteMarkedNotes()
    }
}