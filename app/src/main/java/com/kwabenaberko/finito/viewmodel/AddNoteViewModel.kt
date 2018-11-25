package com.kwabenaberko.finito.viewmodel

import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.repository.NoteRepository
import javax.inject.Inject

class AddNoteViewModel
@Inject constructor (private val noteRepository: NoteRepository) : ObservableViewModel() {
    var newNote = Note(text = "")


    fun saveNewNote(){
        checkFieldsNotEmpty(newNote)
        noteRepository.saveNote(newNote)
    }

    private fun checkFieldsNotEmpty(note: Note){

        if(note.text.isEmpty() || note.color.isEmpty() || note.priority.name.isEmpty()){
            throw IllegalArgumentException("Field cannot be empty")
        }
    }
}