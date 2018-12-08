package com.kwabenaberko.finito.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.kwabenaberko.finito.ContextDispatchers
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.Priority
import com.kwabenaberko.finito.model.repository.NoteRepository
import com.kwabenaberko.finito.viewmodel.util.TestContextDispatchers
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NoteDetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockNoteRepository: NoteRepository

    private lateinit var mNoteDetailViewModel: NoteDetailViewModel

    private lateinit var mTestContextDispatchers: ContextDispatchers

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        mTestContextDispatchers = TestContextDispatchers()
        mNoteDetailViewModel = NoteDetailViewModel(mTestContextDispatchers, mockNoteRepository)
    }

    @Test
    fun testLoadNote() = runBlocking{
        val stubNote = Note(text = "Read book", priority = Priority.HIGH)

        Mockito.`when`(mockNoteRepository.findNoteById(Mockito.anyLong())).thenReturn(stubNote)

        mNoteDetailViewModel.loadNote(32)

        assertEquals(mNoteDetailViewModel.currentNote, stubNote)

    }

    @Test
    fun testUpdateCurrentNote_WhenTextIsInValid_UpdateButtonShouldDisabled() = runBlocking{
        val stubNote = Note(text = "Watch youtube videos", priority = Priority.MEDIUM)
        Mockito.`when`(mockNoteRepository.findNoteById(Mockito.anyLong())).thenReturn(stubNote)
        assertFalse(mNoteDetailViewModel.isUpdateBtnEnabled)

        mNoteDetailViewModel.loadNote(5)

        mNoteDetailViewModel.currentNote.text = ""
        mNoteDetailViewModel.onNoteTextChanged()

        assertFalse(mNoteDetailViewModel.isUpdateBtnEnabled)

    }

    @Test
    fun testUpdateCurrentNote_WhenTextIsValid_UpdateButtonShouldBeEnabled() = runBlocking{
        val stubNote = Note(text = "A stubbed note", priority = Priority.MEDIUM)
        Mockito.`when`(mockNoteRepository.findNoteById(Mockito.anyLong())).thenReturn(stubNote)

        assertFalse(mNoteDetailViewModel.isUpdateBtnEnabled)

        mNoteDetailViewModel.loadNote(5)

        assertTrue(mNoteDetailViewModel.isUpdateBtnEnabled)

    }



    @Test
    fun testUpdateCurrentNote() = runBlocking{
        val stubNote = Note(text = "This is another stubbed note")
        Mockito.`when`(mockNoteRepository.findNoteById(Mockito.anyLong())).thenReturn(stubNote)

        mNoteDetailViewModel.loadNote(15)

        mNoteDetailViewModel.currentNote.text = "This is an updated text"
        mNoteDetailViewModel.currentNote.color = "#2CBE6C"

        mNoteDetailViewModel.updateCurrentNote()

        verify(mockNoteRepository).updateNote(mNoteDetailViewModel.currentNote)
        assertEquals(true, mNoteDetailViewModel.isNoteUpdated.value)

    }


}