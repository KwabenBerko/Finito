package com.kwabenaberko.finito.di.modules

import android.arch.lifecycle.ViewModel
import com.kwabenaberko.finito.viewmodel.AddNoteViewModel
import com.kwabenaberko.finito.viewmodel.NoteDetailViewModel
import com.kwabenaberko.finito.viewmodel.NoteListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NoteListViewModel::class)
    abstract fun bindNoteListViewModel(noteListViewModel: NoteListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddNoteViewModel::class)
    abstract fun bindAddNoteViewModel(addNoteViewModel: AddNoteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NoteDetailViewModel::class)
    abstract fun bindEditNoteViewModel(noteDetailViewModel: NoteDetailViewModel): ViewModel
}