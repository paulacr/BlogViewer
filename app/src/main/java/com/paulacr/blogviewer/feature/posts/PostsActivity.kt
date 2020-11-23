package com.paulacr.blogviewer.feature.posts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.paulacr.blogviewer.ViewState
import com.paulacr.blogviewer.databinding.ActivityDetailBinding
import com.paulacr.domain.Author
import com.paulacr.domain.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {

    private val detailsViewModel: PostsViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val author = intent.extras?.get(AUTHOR_EXTRA_KEY) as Author

        detailsViewModel.postsLiveData.observe(this, {
            when (it) {
                is ViewState.Success -> {
                    Log.i("Authors", "data -> $it.data")
                    setupList(it.data)
                    binding.loadingView.visibility = View.GONE
                    binding.postsList.visibility = View.VISIBLE
                    binding.errorState.visibility = View.GONE
                }
                is ViewState.Loading -> {
                    Log.i("Authors", "loading")
                    binding.loadingView.visibility = View.VISIBLE
                    binding.postsList.visibility = View.GONE
                    binding.errorState.visibility = View.GONE
                }
                is ViewState.Failure -> {
                    binding.loadingView.visibility = View.GONE
                    binding.postsList.visibility = View.GONE
                    binding.errorState.visibility = View.VISIBLE
                }
            }
        })
        detailsViewModel.getPostsByAuthorId(author.id)
    }

    private fun setupList(posts: List<Post>) {
        val recyclerView = binding.postsList
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PostsAdapter(posts)
        recyclerView.addItemDecoration(dividerItemDecoration)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }

    companion object {

        private const val AUTHOR_EXTRA_KEY = "details_extra_key"

        fun detailActivityIntent(activity: Activity, author: Author) =
            Intent(activity, PostsActivity::class.java)
                .putExtra(AUTHOR_EXTRA_KEY, author)
    }
}
