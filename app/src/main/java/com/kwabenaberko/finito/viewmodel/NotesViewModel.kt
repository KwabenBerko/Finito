package com.kwabenaberko.finito.viewmodel

import android.content.res.Resources.NotFoundException
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.repository.NoteRepository
import javax.inject.Inject

class NotesViewModel
@Inject constructor(val noteRepository: NoteRepository) : ObservableViewModel() {
    lateinit var currentNote: Note
    var newNote = Note(text = "")

    fun saveNewNote(){
        checkFieldsNotEmpty(newNote)
        noteRepository.saveNote(newNote)
    }

    fun loadNote(noteId: Int){
        currentNote = noteRepository.findNoteById(noteId) ?: throw NotFoundException()
    }

    fun updateCurrentNote(){
        checkFieldsNotEmpty(currentNote)
        noteRepository.updateNote(currentNote)
    }

    fun deleteCurrentNote(){
        noteRepository.deleteNote(currentNote)
    }

    private fun checkFieldsNotEmpty(note: Note){

        if(note.text.isEmpty() || note.color.isEmpty() || note.priority.name.isEmpty()){
            throw IllegalArgumentException("Field cannot be empty")
        }
    }
}