package com.kwabenaberko.finito.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kwabenaberko.finito.R
import com.kwabenaberko.finito.databinding.ActivityAddNoteBinding
import com.kwabenaberko.finito.viewmodel.AddNoteViewModel
import com.kwabenaberko.finito.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class AddNoteActivity : AppCompatActivity() {
    @Inject
    lateinit var mFactory: ViewModelFactory
    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)
        binding.vm = ViewModelProviders.of(this, mFactory).get(AddNoteViewModel::class.java)
        binding.setLifecycleOwner(this)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "New note"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        binding.vm!!.noteAdded.observe(this, Observer {status ->
            if(status!!){
                finish()
            }
        })
    }


}
