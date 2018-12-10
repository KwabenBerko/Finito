package com.kwabenaberko.finito.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
        @PrimaryKey(autoGenerate = true)
        var noteId: Long = 0,

        @ColumnInfo
        var text: String,

        @ColumnInfo
        var color: String = "#777777",

        @ColumnInfo
        var priority: Priority = Priority.LOW,

        @ColumnInfo
        val createdAt: Long = System.currentTimeMillis()
)