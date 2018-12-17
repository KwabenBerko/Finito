package com.kwabenaberko.finito.model.network

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.kwabenaberko.finito.model.Priority
import com.kwabenaberko.finito.model.repository.network.NoteService
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NoteServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var mNoteService: NoteService

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        mNoteService = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create(
                        GsonBuilder()
                                .registerTypeAdapter(LocalDateTime::class.java, JsonDeserializer {
                                    json, type, context ->
                                    LocalDateTime.parse(json.asJsonPrimitive.asString)
                                })
                                .registerTypeAdapter(Priority::class.java, JsonDeserializer {
                                    json, type, context ->
                                    Priority.values().get(json.asJsonPrimitive.asInt)
                                })
                                .create()
                ))
                .build()
                .create(NoteService::class.java)
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun testGetNoteById(){
        enqueueResponseFromJsonFile("get-single-note.json")
        val note = mNoteService.getNoteById(2).execute().body()
        val request = mockWebServer.takeRequest()
        assertEquals(request.path, "/notes/2")
        assertNotNull(note)
        assertEquals(note?.noteId, 2.toLong())
        assertEquals(note?.text, "Listen to music")
        assertEquals(note?.color, "#FFFFFF")
        assertEquals(note?.priority, Priority.LOW)
        assertEquals(note?.modifiedAt, LocalDateTime.parse("2018-12-14T17:13:30"))
    }

    @Test
    fun testGetSavedNotes(){
        enqueueResponseFromJsonFile("get-note-list.json")
        val notes = mNoteService.getSavedNotes().execute().body()
        val request = mockWebServer.takeRequest()
        assertEquals(request.path, "/notes")
        assertEquals(notes?.size, 3)
        val note = notes!![0]
        assertEquals(note.noteId, 1.toLong())
        assertEquals(note.text, "Eat Breakfast")
        assertEquals(note.color, "#FFFFFF")
        assertEquals(note.priority, Priority.MEDIUM)
        assertEquals(note.modifiedAt, LocalDateTime.parse("2018-12-13T07:15:21"))
    }


    private fun enqueueResponseFromJsonFile(fileName: String){
        val stream = javaClass.classLoader.getResourceAsStream("api-responses/$fileName")
        val source = Okio.buffer(Okio.source(stream))
        mockWebServer.enqueue(MockResponse().setBody(source.readString(Charsets.UTF_8)))
    }
}