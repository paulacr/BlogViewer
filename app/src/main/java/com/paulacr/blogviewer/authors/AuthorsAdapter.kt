package com.paulacr.blogviewer.authors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.paulacr.blogviewer.BaseViewHolder
import com.paulacr.blogviewer.databinding.ItemAuthorBinding
import com.paulacr.domain.Author

class AuthorsAdapter(private val clickListener: (Author) -> Unit) : PagingDataAdapter<Author, BaseViewHolder<*>>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemAuthorBinding.inflate(layoutInflater, parent, false)
        return AuthorViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        getItem(position)?.let { author ->

            (holder as AuthorViewHolder).apply {
                bind(author)
                itemView.setOnClickListener {
                    clickListener(author)
                }
            }
        }
    }

    class AuthorViewHolder(private val binding: ItemAuthorBinding) : BaseViewHolder<Author>(binding) {

        override fun bind(item: Author) {
            binding.author = item
            binding.executePendingBindings()
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Author>() {
            override fun areItemsTheSame(oldItem: Author, newItem: Author): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Author, newItem: Author): Boolean {
                return oldItem == newItem
            }
        }
    }
}
