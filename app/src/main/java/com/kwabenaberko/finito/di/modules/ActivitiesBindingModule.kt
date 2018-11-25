package com.kwabenaberko.finito.di.modules

import com.kwabenaberko.finito.view.AddNoteActivity
import com.kwabenaberko.finito.view.NoteListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModule {
    @ContributesAndroidInjector
    abstract fun noteListActivity(): NoteListActivity

    @ContributesAndroidInjector
    abstract fun addNoteActivity(): AddNoteActivity
}