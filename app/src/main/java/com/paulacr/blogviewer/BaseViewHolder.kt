package com.paulacr.blogviewer

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T>(itemViewBinding: ViewBinding) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    abstract fun bind(item: T)
}
