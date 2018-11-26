package com.kwabenaberko.finito.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.kwabenaberko.finito.R
import com.kwabenaberko.finito.viewmodel.NoteListViewModel
import com.kwabenaberko.finito.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*
import javax.inject.Inject

class NoteListActivity : AppCompatActivity(){

    @Inject
    lateinit var mFactory: ViewModelFactory

    private lateinit var mListViewModel: NoteListViewModel
    private val noteListAdapter = NoteListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        notes_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        notes_recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        notes_recycler_view.adapter = noteListAdapter


        mListViewModel = ViewModelProviders.of(this, mFactory).get(NoteListViewModel::class.java)
        mListViewModel.getNoteList().observe(this, Observer {
            if(it != null){
                noteListAdapter.setNoteList(it)
            }
        })

        fab.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

    }
}
