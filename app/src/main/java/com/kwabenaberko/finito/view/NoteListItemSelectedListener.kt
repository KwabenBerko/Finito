package com.kwabenaberko.finito.view

import com.kwabenaberko.finito.viewmodel.dto.NoteListItem

interface NoteListItemSelectedListener {
    fun onEdit(note: NoteListItem)
    fun onDelete(note: NoteListItem)
}