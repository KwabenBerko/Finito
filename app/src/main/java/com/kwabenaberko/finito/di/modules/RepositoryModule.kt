package com.kwabenaberko.finito.di.modules

import com.kwabenaberko.finito.model.repository.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNoteRepository(): NoteRepository{
        return NoteRepository()
    }
}