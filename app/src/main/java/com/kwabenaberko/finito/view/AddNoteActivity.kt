package com.kwabenaberko.finito.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
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
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "New note"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            onBackPressed()
        }
        return false
    }

}
