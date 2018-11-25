package com.kwabenaberko.finito.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kwabenaberko.finito.R
import com.kwabenaberko.finito.viewmodel.NoteListViewModel
import com.kwabenaberko.finito.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_note_list.*
import javax.inject.Inject

class NoteListActivity : AppCompatActivity() {

    @Inject
    lateinit var mFactory: ViewModelFactory

    private lateinit var mListViewModel: NoteListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        mListViewModel = ViewModelProviders.of(this, mFactory).get(NoteListViewModel::class.java)
        mListViewModel.getNoteList().observe(this, Observer {

        })
    }
}
