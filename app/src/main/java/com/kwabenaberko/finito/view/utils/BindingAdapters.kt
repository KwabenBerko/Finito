package com.kwabenaberko.finito.view.utils

import android.databinding.BindingAdapter
import android.graphics.Color
import android.support.v7.widget.CardView

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("cardBackgroundColor")
        fun setBackgroundColor(view: CardView, colorHex: String){
            view.setCardBackgroundColor(Color.parseColor(colorHex))
        }
    }
}