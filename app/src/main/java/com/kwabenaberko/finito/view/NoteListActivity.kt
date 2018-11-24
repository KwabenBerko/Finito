package com.kwabenaberko.finito.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kwabenaberko.finito.R
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)
    }
}
