package com.paulacr.blogviewer.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.paulacr.blogviewer.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .placeholder(R.mipmap.ic_launcher)
            .into(view)
    }
}