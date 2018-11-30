package com.kwabenaberko.finito.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources.NotFoundException
import com.kwabenaberko.finito.model.Note

class NoteRepository {
    private val NOTES = mutableListOf<Note>()
    private val notesLiveData = MutableLiveData<List<Note>>()

    fun saveNote(note: Note): Note {
        checkFieldsNotEmpty(note)
        NOTES.add(note)
        notesLiveData.postValue(NOTES)
        return note
    }

    fun findNoteById(noteId: Long): Note? {
        return NOTES.filter {
            it.noteId == noteId
        }.firstOrNull()?:throw NotFoundException("Note not found")
    }

    fun updateNote(note: Note): Note? {
        checkFieldsNotEmpty(note)
        val index = NOTES.indexOf(note)
        if(index > -1){
            NOTES[index] = note
            notesLiveData.postValue(NOTES)
        }
        return note
    }

    fun deleteNote(noteId: Long){
        val note = NOTES.filter {
            it.noteId == noteId
        }.firstOrNull() ?: throw NotFoundException("Note not found")

        NOTES.remove(note)
        notesLiveData.postValue(NOTES)
    }

    fun findSavedNotes(): LiveData<List<Note>>{
        notesLiveData.postValue(NOTES)
        return notesLiveData
    }

    private fun checkFieldsNotEmpty(note: Note){

        if(note.text.isEmpty() || note.color.isEmpty() || note.priority.name.isEmpty()){
            throw IllegalArgumentException("Field cannot be empty")
        }
    }
}