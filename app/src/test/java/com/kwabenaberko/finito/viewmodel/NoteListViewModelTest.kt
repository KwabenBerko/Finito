package com.kwabenaberko.finito.viewmodel

import com.kwabenaberko.finito.model.repository.NoteRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NoteListViewModelTest {

    @Mock
    lateinit var mockNoteRepository: NoteRepository

    lateinit var noteListViewModel: NoteListViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        noteListViewModel = NoteListViewModel(mockNoteRepository)
    }

    @Test
    fun deleteNote(){
        mockNoteRepository.deleteNote(2)
        verify(mockNoteRepository).deleteNote(Mockito.anyInt())
    }

}