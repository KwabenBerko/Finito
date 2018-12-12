package com.kwabenaberko.finito.view.util

import android.databinding.BindingAdapter
import android.graphics.Color
import android.support.v7.widget.CardView

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("cardBackgroundColor")
    fun setBackgroundColor(view: CardView, colorHex: String){
        view.setCardBackgroundColor(Color.parseColor(colorHex))
    }
}