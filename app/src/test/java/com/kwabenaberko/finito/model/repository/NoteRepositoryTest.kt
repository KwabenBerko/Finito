package com.kwabenaberko.finito.model.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.res.Resources.NotFoundException
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.Priority
import com.kwabenaberko.finito.model.repository.database.NoteDao
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NoteRepositoryTest{

    @get:Rule
    val taskRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockNoteDao: NoteDao

    @Mock
    lateinit var mockObserver: Observer<List<Note>>

    private lateinit var noteRepository: NoteRepository

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        noteRepository = NoteRepository(mockNoteDao)
    }


    @Test
    fun testSaveNote() = runBlocking<Unit>{
        val note = Note(text = "Practice databinding", color = "#E1ECF4")

        noteRepository.saveNote(note)

        verify(mockNoteDao, times(1)).saveNote(note)
    }

    @Test(expected = NotFoundException::class)
    fun testFindNoteById_WithInvalidNoteId_ShouldThrowException() = runBlocking<Unit>{
        noteRepository.findNoteById(1)
        verify(mockNoteDao, never()).findNoteById(Mockito.anyLong())
    }


    @Test
    fun testFindNoteById() = runBlocking<Unit> {
        val noteStub = Note(text = "Listen to music")
        Mockito.`when`(mockNoteDao.findNoteById(Mockito.anyLong())).thenReturn(noteStub)

        noteRepository.findNoteById(1)

        verify(mockNoteDao, times(1)).findNoteById(Mockito.anyLong())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testUpdateNote_WithInvalidNote_ShouldThrowException() = runBlocking{
        val note = Note(text = "")

        runBlocking {
            noteRepository.updateNote(note)
        }

        verify(mockNoteDao, never()).updateNote(Mockito.any(Note::class.java))
    }

    @Test
    fun testUpdateNote() = runBlocking{
        val note = Note(text = "Buy cornflakes")

        noteRepository.updateNote(note)

        verify(mockNoteDao, times(1)).updateNote(note)
    }

    @Test
    fun testDeleteNote() = runBlocking{
        val noteStub = Note(text = "Read book", priority = Priority.HIGH)

        Mockito.`when`(mockNoteDao.findNoteById(Mockito.anyLong())).thenReturn(noteStub)

        noteRepository.deleteNote(4)

        verify(mockNoteDao, times(1)).deleteNote(noteStub)

    }


    @Test
    fun testFindSavedNotes(){
        val notesLiveData = MutableLiveData<List<Note>>()
        val notes = listOf(
                Note(text = "Eat breakfast", color = "#FF0000", priority = Priority.HIGH),
                Note(text = "Read blog posts"),
                Note(priority = Priority.MEDIUM, text = "Play crossword puzzle")
        )
        notesLiveData.value = notes
        Mockito.`when`(mockNoteDao.findSavedNotes()).thenReturn(notesLiveData)

        noteRepository.findSavedNotes().observeForever(mockObserver)
        verify(mockNoteDao, times(1)).findSavedNotes()
        verify(mockObserver, times(1)).onChanged(Mockito.anyList())

    }

}