package com.kwabenaberko.finito.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.kwabenaberko.finito.model.Note
import com.kwabenaberko.finito.model.repository.NoteRepository
import com.kwabenaberko.finito.viewmodel.dto.NoteListItem
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NoteListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockNoteRepository: NoteRepository

    @Mock
    lateinit var observer: Observer<List<NoteListItem>>

    lateinit var noteListViewModel: NoteListViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        noteListViewModel = NoteListViewModel(mockNoteRepository)
    }

    @Test
    fun testNoteList_WhenNoteIsEmpty_IsNotAddedField_ShouldBeFalse(){
        val liveData = MutableLiveData<List<Note>>()
        Mockito.`when`(mockNoteRepository.findSavedNotes()).thenReturn(liveData)
        noteListViewModel.getNoteList().observeForever(observer)
        verify(observer, never()).onChanged(ArgumentMatchers.anyList())
        assertFalse(noteListViewModel.isNoteListVisible)
    }

    @Test
    fun testNoteList_WhenNoteIsNotEmpty_IsNotAddedField_ShouldBeTrue(){
        val liveData = MutableLiveData<List<Note>>()
        liveData.value = listOf(
                Note(text = "Listen to music")
        )
        Mockito.`when`(mockNoteRepository.findSavedNotes()).thenReturn(liveData)
        noteListViewModel.getNoteList().observeForever(observer)
        verify(observer, times(1)).onChanged(ArgumentMatchers.anyList())
        assertTrue(noteListViewModel.isNoteListVisible)

    }

    @Test
    fun testDeleteNote(){
        noteListViewModel.deleteNote(2)
        runBlocking {
            verify(mockNoteRepository).deleteNote(Mockito.anyLong())
        }
    }

}