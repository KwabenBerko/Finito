package com.kwabenaberko.finito.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.kwabenaberko.finito.model.repository.NoteRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AddNoteViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockNoteRepository: NoteRepository

    private lateinit var mAddNoteViewModel: AddNoteViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        mAddNoteViewModel = AddNoteViewModel(mockNoteRepository)
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun testSaveNote_WithEmptyFields_ShouldThrowException(){
        mAddNoteViewModel.saveNewNote()
    }

    @Test
    fun testSaveNewNote(){
        mAddNoteViewModel.newNote.text = "Buy cornflakes"
        mAddNoteViewModel.newNote.color = "#EAEAEA"
        mAddNoteViewModel.saveNewNote()
        verify(mockNoteRepository).saveNote(mAddNoteViewModel.newNote)
    }


}