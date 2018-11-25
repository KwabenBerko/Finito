package com.kwabenaberko.finito.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.res.Resources
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.Priority
import com.kwabenaberko.finito.model.repository.NoteRepository
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
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

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        mNoteDetailViewModel = NoteDetailViewModel(mockNoteRepository)
    }

    @Test
    fun testLoadNote(){
        val stubNote = Note(text = "Read book", priority = Priority.HIGH)
        Mockito.`when`(mockNoteRepository.findNoteById(Mockito.anyInt())).thenReturn(stubNote)

        mNoteDetailViewModel.loadNote(32)

        assertEquals(mNoteDetailViewModel.currentNote, stubNote)

    }

    @Test(expected = Resources.NotFoundException::class)
    fun testLoadNote_WithInvalidNoteId_ShouldThrowException(){
        mNoteDetailViewModel.loadNote(2)
    }


    @Test(expected = IllegalArgumentException::class)
    fun testUpdateCurrentNote_WithEmptyFields_ShouldThrowException(){
        val stubNote = Note(text = "This is a stubbed note")
        Mockito.`when`(mockNoteRepository.findNoteById(Mockito.anyInt())).thenReturn(stubNote)
        mNoteDetailViewModel.loadNote(15)

        mNoteDetailViewModel.currentNote.text = ""
        mNoteDetailViewModel.currentNote.color = ""

        mNoteDetailViewModel.updateCurrentNote()

    }

    @Test
    fun testUpdateCurrentNote(){
        val stubNote = Note(text = "This is another stubbed note")
        Mockito.`when`(mockNoteRepository.findNoteById(Mockito.anyInt())).thenReturn(stubNote)
        mNoteDetailViewModel.loadNote(15)

        mNoteDetailViewModel.currentNote.text = "This is an updated text"
        mNoteDetailViewModel.currentNote.color = "#2CBE6C"

        mNoteDetailViewModel.updateCurrentNote()
        verify(mockNoteRepository).updateNote(mNoteDetailViewModel.currentNote)

    }


}