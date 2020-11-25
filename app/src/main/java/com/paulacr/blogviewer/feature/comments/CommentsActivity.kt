package com.paulacr.blogviewer.feature.comments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.paulacr.blogviewer.ViewState
import com.paulacr.blogviewer.databinding.ActivityCommentsBinding
import com.paulacr.domain.Author
import com.paulacr.domain.Comment
import com.paulacr.domain.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsActivity : AppCompatActivity() {

    private val commentsViewModel: CommentsViewModel by viewModels()
    private lateinit var binding: ActivityCommentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        commentsViewModel.postsLiveData.observe(this, {
            when (it) {
                is ViewState.Success -> {
                    Log.i("Authors", "data -> $it.data")
                    setupList(it.data)
                    binding.loadingView.visibility = View.GONE
                    binding.commentsList.visibility = View.VISIBLE
                    binding.errorState.visibility = View.GONE
                }
                is ViewState.Loading -> {
                    Log.i("Authors", "loading")
                    binding.loadingView.visibility = View.VISIBLE
                    binding.commentsList.visibility = View.GONE
                    binding.errorState.visibility = View.GONE
                }
                is ViewState.Failure -> {
                    binding.loadingView.visibility = View.GONE
                    binding.commentsList.visibility = View.GONE
                    binding.errorState.visibility = View.VISIBLE
                }
            }
        })

        val author = intent.extras?.get(AUTHOR_EXTRA_KEY) as Author?
        val post = intent.extras?.get(POST_EXTRA_KEY) as Post
        author?.let {
            commentsViewModel.getCommentsByUsername(post.id)
        }
        setPostInfo(post)
    }

    private fun setupList(comments: List<Comment>) {
        val recyclerView = binding.commentsList
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = CommentsAdapter(comments)
    }

    private fun setPostInfo(post: Post) {
        Glide.with(this)
            .load(post.postUrl)
            .into(binding.postInfo.postImageUrl)

        binding.postInfo.post = post
    }

    companion object {

        private const val AUTHOR_EXTRA_KEY = "author_extra_key"
        private const val POST_EXTRA_KEY = "post_extra_key"

        fun commentActivityIntent(activity: Activity, author: Author, post: Post) =
            Intent(activity, CommentsActivity::class.java)
                .putExtra(AUTHOR_EXTRA_KEY, author)
                .putExtra(POST_EXTRA_KEY, post)
    }
}
