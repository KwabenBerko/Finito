package com.kwabenaberko.finito.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.kwabenaberko.finito.model.repository.NoteRepository
import com.kwabenaberko.finito.viewmodel.dto.NoteListItem
import javax.inject.Inject

class NoteListViewModel
@Inject constructor(val noteRepository: NoteRepository) : ViewModel(){
    fun getNoteList(): LiveData<List<NoteListItem>>{
        return Transformations.map(noteRepository.findSavedNotes()) { savedNotes ->
            savedNotes.map {
                NoteListItem(
                        noteId = it.noteId,
                        text = it.text,
                        color = it.color
                )
            }
        }
    }

    fun deleteNote(noteId: Int){
        noteRepository.deleteNote(noteId)
    }

}