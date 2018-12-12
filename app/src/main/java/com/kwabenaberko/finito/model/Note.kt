package com.kwabenaberko.finito.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.LocalDateTime

@Entity(tableName = "notes")
data class Note(
        @PrimaryKey(autoGenerate = true)
        var noteId: Long = 0,

        @ColumnInfo
        var text: String,

        @ColumnInfo
        var color: String = "#FFFFFF",

        @ColumnInfo
        var priority: Priority = Priority.LOW,

        @ColumnInfo
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)