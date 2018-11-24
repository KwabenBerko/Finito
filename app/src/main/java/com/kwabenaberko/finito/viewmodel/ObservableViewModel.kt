package com.kwabenaberko.finito.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import com.kwabenaberko.finito.BR

open class ObservableViewModel : ViewModel(), Observable {
    private val mCallbacks = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.remove(callback)
    }

    fun notifyChange(){
        mCallbacks.notifyChange(this, BR._all)
    }

    fun notifyPropertyChanged(fieldId: Int){
        mCallbacks.notifyChange(this, fieldId)
    }

}