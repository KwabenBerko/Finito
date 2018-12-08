package com.kwabenaberko.finito

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.Priority
import com.kwabenaberko.finito.model.repository.database.NoteDao
import com.kwabenaberko.finito.model.repository.database.NoteDatabase
import com.kwabenaberko.finito.util.LiveDataTestUtil
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteDaoTest {

    private lateinit var mNoteDatabase: NoteDatabase
    private lateinit var mNoteDao: NoteDao

    @Before
    fun setup(){
        val context = InstrumentationRegistry.getContext()
        mNoteDatabase = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java).build()
        mNoteDao = mNoteDatabase.getNoteDao()

    }

    @After
    fun tearDown(){
        mNoteDatabase.close()
    }

    @Test
    fun testFindNoteById_WithInvalidNoteId_ShouldReturnNull(){
        assertNull(mNoteDao.findNoteById(5))
    }

    @Test
    fun testSaveNote(){
        val note = Note(text = "Have a goodnight sleep", priority = Priority.HIGH)
        val noteId: Long = mNoteDao.saveNote(note)

        val savedNote = mNoteDao.findNoteById(noteId)

        assertNotNull(savedNote)
        assertEquals(note.copy(noteId = noteId), savedNote)
    }

    @Test
    fun testFindSavedNotes(){
        val notes = listOf(
                Note(text = "Read book"),
                Note(text = "Listen to music", color = "#00FFFF"),
                Note(priority = Priority.MEDIUM, text = "Do Laundry")
        )

        notes.forEach{
            mNoteDao.saveNote(it)
        }

        val savedNotes = LiveDataTestUtil.getValue(mNoteDao.findSavedNotes())
        assertEquals(3, savedNotes.size)
    }

    @Test
    fun testUpdateNote(){

        val note =  Note(text = "Listen to music", color = "#00FFFF")
        val noteId = mNoteDao.saveNote(note)
        val savedNote = mNoteDao.findNoteById(noteId)

        val newText = "Play subway surfers"
        savedNote?.text = newText
        mNoteDao.updateNote(savedNote!!)

        val updatedNote = mNoteDao.findNoteById(noteId)

        assertEquals(savedNote, updatedNote)
    }


    @Test
    fun testDeleteNote(){
        val note =  Note(text = "Listen to music", color = "#00FFFF")
        val noteId = mNoteDao.saveNote(note)
        val savedNote = mNoteDao.findNoteById(noteId)

        mNoteDao.deleteNote(savedNote!!)

        assertNull(mNoteDao.findNoteById(noteId))
    }
}