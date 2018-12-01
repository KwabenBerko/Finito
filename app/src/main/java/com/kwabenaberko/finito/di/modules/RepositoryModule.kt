package com.kwabenaberko.finito.di.modules

import com.kwabenaberko.finito.AppExecutors
import com.kwabenaberko.finito.model.repository.NoteRepository
import com.kwabenaberko.finito.model.repository.database.NoteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppExecutorModule::class, DatabaseModule::class])
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNoteRepository(appExecutors: AppExecutors, notesDao: NoteDao): NoteRepository{
        return NoteRepository(appExecutors, notesDao)
    }
}