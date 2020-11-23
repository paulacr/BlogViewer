package com.paulacr.blogviewer.feature.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paulacr.blogviewer.BaseViewHolder
import com.paulacr.blogviewer.databinding.ItemPostsBinding
import com.paulacr.domain.Post

class PostsAdapter(private val postsList: List<Post>) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemPostsBinding.inflate(layoutInflater, parent, false)
        return PostViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        postsList[position].apply {
            (holder as PostViewHolder).bind(this)
        }
    }

    class PostViewHolder(private val binding: ItemPostsBinding) : BaseViewHolder<Post>(binding) {

        override fun bind(item: Post) {
            binding.post = item
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int = postsList.size
}
