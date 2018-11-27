package com.kwabenaberko.finito.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.kwabenaberko.finito.model.repository.NoteRepository
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.*
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

    @Test
    fun testSaveNote_WhenTextIsValid_AddButtonShouldBeEnabled(){
        mAddNoteViewModel.newNote.text = "A Test"
        assertFalse(mAddNoteViewModel.isAddBtnEnabled)

        mAddNoteViewModel.onNoteTextChanged()

        assertTrue(mAddNoteViewModel.isAddBtnEnabled)
    }

    @Test
    fun testSaveNewNote(){
        mAddNoteViewModel.newNote.text = "Buy cornflakes"
        mAddNoteViewModel.newNote.color = "#EAEAEA"
        mAddNoteViewModel.saveNewNote()


        verify(mockNoteRepository).saveNote(mAddNoteViewModel.newNote)
        assertEquals(true, mAddNoteViewModel.isNoteAdded.value)
    }


}