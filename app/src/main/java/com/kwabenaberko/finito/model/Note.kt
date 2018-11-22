package com.kwabenaberko.finito.model

data class Note(
        private val noteId: Int,
        private val text: String,
        private val color: String = "#EAEAEA",
        private val priority: Priority = Priority.LOW,
        private val createdAt: Long = System.currentTimeMillis()
)