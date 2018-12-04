package com.kwabenaberko.finito.model.repository

import android.arch.lifecycle.LiveData
import android.content.res.Resources.NotFoundException
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.repository.database.NoteDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteRepository
@Inject constructor(
        private val mNotesDao: NoteDao
){
    suspend fun saveNote(note: Note): Note {
        checkFieldsNotEmpty(note)
        return GlobalScope.async {
            val noteId = mNotesDao.saveNote(note)
            note.copy(noteId = noteId)
        }.await()
    }

    suspend fun findNoteById(noteId: Long): Note {
        return GlobalScope.async {
            mNotesDao.findNoteById(noteId)
                    ?:throw NotFoundException("Note not found")
        }.await()
    }

    suspend fun updateNote(note: Note): Note {
        checkFieldsNotEmpty(note)
        return GlobalScope.async {
            mNotesDao.updateNote(note)
            note
        }.await()
    }

    suspend fun deleteNote(noteId: Long){
        GlobalScope.launch {
            val noteToDelete = findNoteById(noteId)
            mNotesDao.deleteNote(noteToDelete)
        }
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