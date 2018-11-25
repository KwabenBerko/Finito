package com.kwabenaberko.finito.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kwabenaberko.finito.model.Note

class NoteRepository {
    private val NOTES = mutableListOf<Note>(
            Note(text = "Watch Youtube Videos"),
            Note(text = "Have A Good Sleep")
    )
    private val notesLiveData = MutableLiveData<List<Note>>()

    fun saveNote(note: Note): Note {
        NOTES.add(note)
        return note
    }

    fun findNoteById(noteId: Int): Note? {
        return NOTES.filter {
            it.noteId == noteId
        }.firstOrNull()
    }

    fun updateNote(note: Note): Note? {
        val index = NOTES.indexOf(note)
        if(index > -1){
            NOTES[index] = note
        }
        return note
    }

    fun deleteNote(noteId: Int){
        val note = NOTES.filter {
            it.noteId == noteId
        }.firstOrNull()

        if(note != null){
            NOTES.remove(note)
        }
    }

    fun findSavedNotes(): LiveData<List<Note>>{
        notesLiveData.postValue(NOTES)
        return notesLiveData
    }

}