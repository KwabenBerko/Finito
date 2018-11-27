package com.kwabenaberko.finito.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.databinding.Bindable
import com.kwabenaberko.finito.BR
import com.kwabenaberko.finito.model.repository.NoteRepository
import com.kwabenaberko.finito.viewmodel.dto.NoteListItem
import javax.inject.Inject

class NoteListViewModel
@Inject constructor(val noteRepository: NoteRepository) : ObservableViewModel(){
    @get:Bindable
    var isNoteListVisible = false

    fun getNoteList():LiveData<List<NoteListItem>> = Transformations.map(noteRepository.findSavedNotes()) { savedNotes ->

        isNoteListVisible = savedNotes.isNotEmpty()
        notifyPropertyChanged(BR.noteListVisible)

        savedNotes.map {
            NoteListItem(
                    noteId = it.noteId,
                    text = it.text,
                    color = it.color
            )
        }

    }


    fun deleteNote(noteId: Int){
        noteRepository.deleteNote(noteId)
    }

}