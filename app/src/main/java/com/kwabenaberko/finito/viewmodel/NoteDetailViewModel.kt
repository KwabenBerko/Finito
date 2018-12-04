package com.kwabenaberko.finito.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import android.databinding.Bindable
import com.kwabenaberko.finito.BR
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.repository.NoteRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class NoteDetailViewModel
@Inject constructor(private val noteRepository: NoteRepository): ObservableViewModel() {
    @get:Bindable var isUpdateBtnEnabled = false
    @get:Bindable var currentNote = Note(text = "")
    var isNoteUpdated = MutableLiveData<Boolean>()

    private fun validateNote(){
        isUpdateBtnEnabled = currentNote.text.trim().length > 3
        notifyPropertyChanged(BR.updateBtnEnabled)
    }

    fun onNoteTextChanged(){
        validateNote()
    }

    fun loadNote(noteId: Long) = runBlocking{
        currentNote = noteRepository.findNoteById(noteId) ?: throw Resources.NotFoundException()
        validateNote()
        notifyPropertyChanged(BR.currentNote)
    }

    fun updateCurrentNote() = runBlocking{
        isNoteUpdated.postValue(
                try{
                    noteRepository.updateNote(currentNote)
                    true
                }
                catch (t: Throwable){
                    false
                }
        )
    }

}