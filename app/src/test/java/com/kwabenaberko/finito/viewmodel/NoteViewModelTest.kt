package com.kwabenaberko.finito.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.res.Resources.NotFoundException
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

class NoteViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockNoteRepository: NoteRepository

    private lateinit var notesViewModel: NotesViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        notesViewModel = NotesViewModel(mockNoteRepository)
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun testSaveNote_WithEmptyFields_ShouldThrowException(){
        notesViewModel.saveNewNote()
    }

    @Test
    fun testSaveNewNote(){
        notesViewModel.newNote.text = "Buy cornflakes"
        notesViewModel.newNote.color = "#EAEAEA"
        notesViewModel.saveNewNote()
        verify(mockNoteRepository).saveNote(notesViewModel.newNote)
    }

    @Test(expected = NotFoundException::class)
    fun testLoadNote_WithInvalidNoteId_ShouldThrowException(){
        notesViewModel.loadNote(2)
    }

    @Test
    fun testLoadNote(){
        val stubNote = Note(text = "Read book", priority = Priority.HIGH)
        Mockito.`when`(mockNoteRepository.findNoteById(Mockito.anyInt())).thenReturn(stubNote)

        notesViewModel.loadNote(32)

        assertEquals(notesViewModel.currentNote, stubNote)

    }

    @Test(expected = IllegalArgumentException::class)
    fun testUpdateCurrentNote_WithEmptyFields_ShouldThrowException(){
        val stubNote = Note(text = "This is a stubbed note")
        Mockito.`when`(mockNoteRepository.findNoteById(Mockito.anyInt())).thenReturn(stubNote)
        notesViewModel.loadNote(15)

        notesViewModel.currentNote.text = ""
        notesViewModel.currentNote.color = ""

        notesViewModel.updateCurrentNote()

    }

    @Test
    fun testUpdateCurrentNote(){
        val stubNote = Note(text = "This is another stubbed note")
        Mockito.`when`(mockNoteRepository.findNoteById(Mockito.anyInt())).thenReturn(stubNote)
        notesViewModel.loadNote(15)

        notesViewModel.currentNote.text = "This is an updated text"
        notesViewModel.currentNote.color = "#2CBE6C"

        notesViewModel.updateCurrentNote()
        verify(mockNoteRepository).updateNote(notesViewModel.currentNote)

    }

    @Test
    fun deleteNote(){
        val stubNote = Note(text = "This is yet another stubbed note")
        Mockito.`when`(mockNoteRepository.findNoteById(Mockito.anyInt())).thenReturn(stubNote)
        notesViewModel.loadNote(15)

        notesViewModel.deleteCurrentNote()

        verify(mockNoteRepository).deleteNote(notesViewModel.currentNote)
    }

}