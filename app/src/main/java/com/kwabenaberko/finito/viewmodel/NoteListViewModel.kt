package com.kwabenaberko.finito.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.databinding.Bindable
import com.kwabenaberko.finito.BR
import com.kwabenaberko.finito.ContextDispatchers
import com.kwabenaberko.finito.model.repository.NoteRepository
import com.kwabenaberko.finito.viewmodel.dto.NoteListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NoteListViewModel
@Inject constructor(
        private val contextDispatchers: ContextDispatchers,
        private val noteRepository: NoteRepository) :
        ObservableViewModel(), CoroutineScope{
    @get:Bindable
    var isNoteListVisible = false

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + contextDispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init{
        //For Testing Purposes
        deleteSavedNotes()
    }

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

    fun deleteSavedNotes() = scope.launch {
        async(contextDispatchers.IO) { noteRepository.deleteSavedNotes() }.await()
    }

    fun deleteNote(noteId: Long) = scope.launch{
        async(contextDispatchers.IO) {noteRepository.deleteNote(noteId)}.await()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}