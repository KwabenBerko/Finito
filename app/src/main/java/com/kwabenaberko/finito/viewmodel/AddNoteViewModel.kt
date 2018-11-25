package com.kwabenaberko.finito.viewmodel

import android.databinding.Bindable
import android.util.Log
import com.android.databinding.library.baseAdapters.BR
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.repository.NoteRepository
import javax.inject.Inject

class AddNoteViewModel
@Inject constructor (private val noteRepository: NoteRepository) : ObservableViewModel() {
    @get:Bindable
    var newNote = Note(text = "")

    fun saveNewNote(){
        checkFieldsNotEmpty(newNote)
        noteRepository.saveNote(newNote)
    }

    fun onNoteTextChanged(){
        Log.d("ADDNOTEVIEWMODEL", "Text Changed Called!")
        notifyPropertyChanged(BR.newNote)
    }

    private fun checkFieldsNotEmpty(note: Note){

        if(note.text.isEmpty() || note.color.isEmpty() || note.priority.name.isEmpty()){
            throw IllegalArgumentException("Field cannot be empty")
        }
    }
}