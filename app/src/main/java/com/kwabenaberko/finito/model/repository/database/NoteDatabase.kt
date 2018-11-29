package com.kwabenaberko.finito.model.repository.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kwabenaberko.finito.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun getNoteDao(): NoteDao
}