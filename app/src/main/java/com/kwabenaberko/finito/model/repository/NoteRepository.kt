package com.kwabenaberko.finito.model.repository

import android.arch.lifecycle.LiveData
import android.content.res.Resources.NotFoundException
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.repository.database.NoteDao
import javax.inject.Inject

class NoteRepository
@Inject constructor(
        private val mNotesDao: NoteDao
){
    fun saveNote(note: Note): Note {
        checkFieldsNotEmpty(note)
        val noteId = mNotesDao.saveNote(note)
        return note.copy(noteId = noteId)
    }

    suspend fun findNoteById(noteId: Long): Note {
        return mNotesDao.findNoteById(noteId)
                ?:throw NotFoundException("Note not found")
    }

    suspend fun updateNote(note: Note): Note {
        checkFieldsNotEmpty(note)
        mNotesDao.updateNote(note)
        return note
    }

    suspend fun deleteNote(noteId: Long){
        val noteToDelete = findNoteById(noteId)
        mNotesDao.deleteNote(noteToDelete)
    }

    fun findSavedNotes(): LiveData<List<Note>>{
        return mNotesDao.findSavedNotes()
    }

    private fun checkFieldsNotEmpty(note: Note){

        if(note.text.isEmpty() || note.color.isEmpty() || note.priority.name.isEmpty()){
            throw IllegalArgumentException("Field cannot be empty")
        }
    }
}