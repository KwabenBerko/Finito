package com.kwabenaberko.finito.viewmodel

import android.content.res.Resources
import android.databinding.Bindable
import com.kwabenaberko.finito.BR
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.repository.NoteRepository
import javax.inject.Inject

class NoteDetailViewModel
@Inject constructor(private val noteRepository: NoteRepository): ObservableViewModel() {
    @get:Bindable
    lateinit var currentNote: Note

    fun loadNote(noteId: Int){
        currentNote = noteRepository.findNoteById(noteId) ?: throw Resources.NotFoundException()
        notifyPropertyChanged(BR.currentNote)
    }

    fun updateCurrentNote(){
        checkFieldsNotEmpty(currentNote)
        noteRepository.updateNote(currentNote)
    }

    private fun checkFieldsNotEmpty(note: Note){

        if(note.text.isEmpty() || note.color.isEmpty() || note.priority.name.isEmpty()){
            throw IllegalArgumentException("Field cannot be empty")
        }
    }
}