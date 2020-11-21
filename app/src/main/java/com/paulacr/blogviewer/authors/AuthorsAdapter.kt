package com.paulacr.blogviewer.authors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paulacr.blogviewer.BaseViewHolder
import com.paulacr.blogviewer.databinding.ItemAuthorBinding
import com.paulacr.domain.Author

class AuthorsAdapter(private val authorsList: List<Author>, listener: (Int, Author) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemAuthorBinding.inflate(layoutInflater, parent, false)
        return AuthorViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = authorsList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as AuthorViewHolder).bind(authorsList[position])
    }

    class AuthorViewHolder(private val binding: ItemAuthorBinding) : BaseViewHolder<Author>(binding) {

        override fun bind(item: Author) {
            binding.author = item
            binding.executePendingBindings()
        }
    }
}
