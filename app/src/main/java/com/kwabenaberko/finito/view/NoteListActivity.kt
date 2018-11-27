package com.kwabenaberko.finito.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.kwabenaberko.finito.R
import com.kwabenaberko.finito.databinding.ActivityNoteListBinding
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
    private lateinit var noteListAdapter: NoteListAdapter
    private lateinit var binding:ActivityNoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_list)
        setSupportActionBar(binding.toolbar)

        mListViewModel = ViewModelProviders.of(this, mFactory).get(NoteListViewModel::class.java)
        binding.vm = mListViewModel
        binding.setLifecycleOwner(this)

        noteListAdapter = NoteListAdapter {
            mListViewModel.deleteNote(it.noteId)
        }


        mListViewModel.getNoteList().observe(this, Observer {
            if(it != null){
                noteListAdapter.setNoteList(it)
            }
        })

        notes_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        notes_recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        notes_recycler_view.adapter = noteListAdapter


        fab.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

    }
}
