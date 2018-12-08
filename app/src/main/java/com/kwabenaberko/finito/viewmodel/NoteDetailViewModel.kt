package com.kwabenaberko.finito.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import android.databinding.Bindable
import com.kwabenaberko.finito.BR
import com.kwabenaberko.finito.ContextDispatchers
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NoteDetailViewModel
@Inject constructor(
        private val contextDispatchers: ContextDispatchers,
        private val noteRepository: NoteRepository
): ObservableViewModel(), CoroutineScope{
    @get:Bindable var isUpdateBtnEnabled = false
    @get:Bindable var currentNote = Note(text = "")
    var isNoteUpdated = MutableLiveData<Boolean>()

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + contextDispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private fun validateNote(){
        isUpdateBtnEnabled = currentNote.text.trim().length > 3
        notifyPropertyChanged(BR.updateBtnEnabled)
    }

    fun onNoteTextChanged(){
        validateNote()
    }

    fun loadNote(noteId: Long) = scope.launch{
        currentNote = async(contextDispatchers.IO) {
             noteRepository.findNoteById(noteId) ?: throw Resources.NotFoundException()
        }.await()
        validateNote()
        notifyPropertyChanged(BR.currentNote)
    }

    fun updateCurrentNote() = scope.launch{
        isNoteUpdated.postValue(
                try{
                    async(contextDispatchers.IO) { noteRepository.updateNote(currentNote) }.await()
                    true
                }
                catch (t: Throwable){
                    false
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}