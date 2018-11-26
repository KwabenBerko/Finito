package com.kwabenaberko.finito.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.kwabenaberko.finito.BR
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.repository.NoteRepository
import javax.inject.Inject

class AddNoteViewModel
@Inject constructor (private val noteRepository: NoteRepository) : ObservableViewModel() {
    @get:Bindable var addBtnEnabled = false
    var noteAdded = MutableLiveData<Boolean>()
    var newNote = Note(text = "")



    fun onNoteTextChanged(){
        addBtnEnabled = newNote.text.trim().length > 3
        notifyPropertyChanged(BR.addBtnEnabled)
    }

    fun saveNewNote(){
        noteAdded.postValue(
                try{
                    noteRepository.saveNote(newNote)
                    true
                }
                catch (e: Throwable){
                    false
                }
        )
    }


}