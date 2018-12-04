package com.kwabenaberko.finito.di.modules

import com.kwabenaberko.finito.model.repository.NoteRepository
import com.kwabenaberko.finito.model.repository.database.NoteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNoteRepository(notesDao: NoteDao): NoteRepository{
        return NoteRepository(notesDao)
    }
}