package com.kwabenaberko.finito.model.repository

import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.Priority
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NoteRepositoryTest {

    private lateinit var noteRepository: NoteRepository

    @Before
    fun setUp(){
        noteRepository = NoteRepository()
    }

    @Test(expected = IllegalArgumentException::class)
    fun testSaveNote_WithEmptyFields_ShouldThrowException(){
        val note = Note(text = "")
        noteRepository.saveNote(note)
    }

    @Test
    fun testSaveNote(){
        val note = Note(text = "Practice databinding", color = "#E1ECF4")

        val savedNote = noteRepository.saveNote(note)

        assertEquals(savedNote, noteRepository.findNoteById(savedNote.noteId))
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun testUpdateNote_WithEmptyFields_ShouldThrowException(){
        val savedNote = noteRepository.saveNote(Note(text = "Buy cornflakes"))
        savedNote.text = ""
        savedNote.color = ""

        noteRepository.updateNote(savedNote)
    }

    @Test
    fun testUpdateNote(){
        val savedNote = noteRepository.saveNote(Note(text = "Buy cornflakes"))
        savedNote.text = "Water crops"
        savedNote.priority = Priority.HIGH

        val updatedNote = noteRepository.updateNote(savedNote)

        assertEquals(savedNote, updatedNote)
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

        val savedNotes = noteRepository.findSavedNotes()

        assertEquals(3, savedNotes.size)
    }

}