package com.kwabenaberko.finito.model

import java.util.*

data class Note(
        var text: String,
        var color: String = "#EAEAEA",
        var priority: Priority = Priority.LOW
){
    private val _noteId: Int = Random().nextInt(Int.MAX_VALUE)
    private val _createdAt: Long = System.currentTimeMillis()

    val noteId: Int
        get() = _noteId

    val createdAt: Long get() = _createdAt
}