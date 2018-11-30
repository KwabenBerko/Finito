package com.kwabenaberko.finito

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.Priority
import com.kwabenaberko.finito.model.repository.database.NoteDao
import com.kwabenaberko.finito.model.repository.database.NoteDatabase
import junit.framework.Assert.assertEquals
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
    fun testSaveNote(){
        val note = Note(text = "Have a goodnight sleep", priority = Priority.HIGH)
        val noteId: Long = mNoteDao.saveNote(note)

        val savedNote = mNoteDao.findNoteById(noteId)

        assertEquals(note.copy(noteId = noteId), savedNote)
    }

    @Test
    fun testFindSavedNotes(){

    }
}