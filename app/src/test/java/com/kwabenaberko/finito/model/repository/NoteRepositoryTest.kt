package com.kwabenaberko.finito.model.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.res.Resources.NotFoundException
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.Priority
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteRepositoryTest {

    @get:Rule
    val taskRule = InstantTaskExecutorRule()
    private lateinit var noteRepository: NoteRepository

    @Before
    fun setUp(){
        noteRepository = NoteRepository()
    }


    @Test
    fun testSaveNote(){
        val note = Note(text = "Practice databinding", color = "#E1ECF4")

        val savedNote = noteRepository.saveNote(note)

        assertEquals(savedNote, noteRepository.findNoteById(savedNote.noteId))
    }

    @Test(expected = NotFoundException::class)
    fun testFindNoteById_WithInvalidNoteId_ShouldReturnNull(){
        noteRepository.findNoteById(1)
    }



    @Test
    fun testUpdateNote(){
        val savedNote = noteRepository.saveNote(Note(text = "Buy cornflakes"))
        savedNote.text = "Water crops"
        savedNote.priority = Priority.HIGH

        val updatedNote = noteRepository.updateNote(savedNote)

        assertEquals(savedNote, updatedNote)
    }

    @Test(expected = NotFoundException::class)
    fun testDeleteNote(){
        val note = Note(text = "A note to be deleted")
        val savedNote = noteRepository.saveNote(note)

        noteRepository.deleteNote(savedNote.noteId)

        noteRepository.findNoteById(savedNote.noteId)

    }


    @Test
    fun testFindSavedNotes(){
        val notes = listOf(
                Note(text = "Eat breakfast", color = "#FF0000", priority = Priority.HIGH),
                Note(text = "Read blog posts"),
                Note(priority = Priority.MEDIUM, text = "Play crossword puzzle")
        )

        notes.forEach {
            noteRepository.saveNote(it)
        }

        noteRepository.findSavedNotes().observeForever{
            assertEquals(3, it?.size)
        }


    }

}