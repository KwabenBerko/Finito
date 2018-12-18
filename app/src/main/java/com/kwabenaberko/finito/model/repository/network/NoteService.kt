package com.kwabenaberko.finito.model.repository.network

import com.kwabenaberko.finito.model.Note
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface NoteService {

    @GET("/notes")
    fun getSavedNotes(): Call<List<Note>>

    @GET("/notes/{noteId}")
    fun getNoteById(@Path("noteId") noteId: Long): Call<Note>

    @POST("/notes")
    fun saveNote(@Body note: Note): Call<Note>

    @PATCH("/notes/{noteId}")
    fun updateNote(@Path("noteId") noteId: Long, @Body partialNote: Map<String, String>): Call<Note>

    @DELETE("notes/{noteId}")
    fun deleteNoteById(@Path("noteId") noteId: Long): Call<ResponseBody>

}