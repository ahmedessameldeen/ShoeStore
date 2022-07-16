package com.udacity.shoestore.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ViewExtensions {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun ImageView.loadImage(url: String) = Glide.with(this.context).load(url).into(this)
}