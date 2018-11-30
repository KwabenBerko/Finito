package com.kwabenaberko.finito.model.repository.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.kwabenaberko.finito.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun findSavedNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE noteId = :noteId")
    fun findNoteById(noteId: Long): Note

    @Insert
    fun saveNote(note: Note): Long

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)


}