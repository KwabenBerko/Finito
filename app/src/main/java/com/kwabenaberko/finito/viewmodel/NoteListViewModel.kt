package com.kwabenaberko.finito.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.repository.NoteRepository
import javax.inject.Inject

class NoteListViewModel
@Inject constructor(val noteRepository: NoteRepository) : ViewModel(){
    fun getNoteList(): LiveData<List<Note>>{
        return noteRepository.findSavedNotes()
    }

    fun deleteCurrentNote(noteId: Int){
        noteRepository.deleteNote(noteId)
    }

}