package com.kwabenaberko.finito.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kwabenaberko.finito.model.Note

class NoteRepository {
    private val NOTES = mutableListOf<Note>()
    private val notesLiveData = MutableLiveData<List<Note>>()

    fun saveNote(note: Note): Note {
        checkFieldsNotEmpty(note)
        NOTES.add(note)
        return note
    }

    fun findNoteById(noteId: Int): Note {
        return NOTES.filter {
            it.noteId == noteId
        }[0]
    }

    fun updateNote(note: Note): Note {
        checkFieldsNotEmpty(note)
        val index = NOTES.indexOf(note)
        NOTES[index] = note
        return note
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