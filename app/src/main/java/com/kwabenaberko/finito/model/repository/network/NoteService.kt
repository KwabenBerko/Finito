package com.kwabenaberko.finito.model.repository.network

import com.kwabenaberko.finito.model.Note
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NoteService {

    @GET("/notes")
    fun getSavedNotes(): Call<List<Note>>

    @GET("/notes/{noteId}")
    fun getNoteById(@Path("noteId") noteId: Int): Call<Note>

    @POST("/notes")
    fun saveNote(@Body note: Note): Call<Note>

}