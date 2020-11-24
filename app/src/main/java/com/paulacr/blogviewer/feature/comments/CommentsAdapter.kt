package com.paulacr.blogviewer.feature.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paulacr.blogviewer.BaseViewHolder
import com.paulacr.blogviewer.databinding.ItemCommentsBinding
import com.paulacr.domain.Comment

class CommentsAdapter(private val commentsList: List<Comment>) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemCommentsBinding.inflate(layoutInflater, parent, false)
        return CommentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        commentsList[position].apply {
            (holder as CommentViewHolder).bind(this)
        }
    }

    class CommentViewHolder(private val binding: ItemCommentsBinding) : BaseViewHolder<Comment>(binding) {

        override fun bind(item: Comment) {
            binding.comment = item
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int = commentsList.size
}
