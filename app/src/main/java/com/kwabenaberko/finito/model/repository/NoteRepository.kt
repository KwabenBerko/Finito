package com.kwabenaberko.finito.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kwabenaberko.finito.model.Note

class NoteRepository {
    private val NOTES = mutableListOf<Note>()
    private val notesLiveData = MutableLiveData<List<Note>>()

    fun saveNote(note: Note): Note {
        NOTES.add(note)
        return note
    }

    fun findNoteById(noteId: Int): Note? {
        val filtered = NOTES.filter {
            it.noteId == noteId
        }

        return if (!filtered.isEmpty()) filtered[0] else null
    }

    fun updateNote(note: Note): Note? {
        val index = NOTES.indexOf(note)
        if(index > -1){
            NOTES[index] = note
        }
        return note
    }

    fun deleteNote(note: Note){
        val index = NOTES.indexOf(note)
        if(index > -1){
            NOTES.removeAt(index)
        }
    }

    fun findSavedNotes(): LiveData<List<Note>>{
        notesLiveData.postValue(NOTES)
        return notesLiveData
    }

}