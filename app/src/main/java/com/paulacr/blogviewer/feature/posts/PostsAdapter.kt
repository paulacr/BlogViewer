package com.paulacr.blogviewer.feature.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paulacr.blogviewer.BaseViewHolder
import com.paulacr.blogviewer.databinding.ItemPostsBinding
import com.paulacr.domain.Post

class PostsAdapter(private val postsList: List<Post>, private val clickListener: (Post) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemPostsBinding.inflate(layoutInflater, parent, false)
        return PostViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = postsList[position]
        (holder as PostViewHolder).apply {
            itemView.setOnClickListener {
                clickListener(item)
            }
        }.bind(item)
    }

    override fun getItemCount(): Int = postsList.size
}

class PostViewHolder(private val binding: ItemPostsBinding) : BaseViewHolder<Post>(binding) {

    override fun bind(item: Post) {
        binding.post = item
        binding.executePendingBindings()
    }
}
