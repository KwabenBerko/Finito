package com.kwabenaberko.finito.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.kwabenaberko.finito.ContextDispatchers
import com.kwabenaberko.finito.model.repository.NoteRepository
import com.kwabenaberko.finito.viewmodel.util.TestContextDispatchers
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
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

    private lateinit var mTestContextDispatchers: ContextDispatchers

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        mTestContextDispatchers = TestContextDispatchers()
        mAddNoteViewModel = AddNoteViewModel(mTestContextDispatchers, mockNoteRepository)
    }

    @Test
    fun testSaveNote_WhenTextIsValid_AddButtonShouldBeEnabled(){
        mAddNoteViewModel.newNote.text = "A Test"
        assertFalse(mAddNoteViewModel.isAddBtnEnabled)

        mAddNoteViewModel.onNoteTextChanged()

        assertTrue(mAddNoteViewModel.isAddBtnEnabled)
    }

    @Test
    fun testSaveNewNote() = runBlocking{
        mAddNoteViewModel.newNote.text = "Buy cornflakes"
        mAddNoteViewModel.newNote.color = "#EAEAEA"
        mAddNoteViewModel.saveNewNote()

        verify(mockNoteRepository).saveNote(mAddNoteViewModel.newNote)
        assertEquals(true, mAddNoteViewModel.isNoteAdded.value)
    }


}