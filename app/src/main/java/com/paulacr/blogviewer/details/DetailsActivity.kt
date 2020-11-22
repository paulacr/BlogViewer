package com.paulacr.blogviewer.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.paulacr.blogviewer.ViewState
import com.paulacr.blogviewer.databinding.ActivityDetailBinding
import com.paulacr.domain.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    val detailsViewModel: PostsViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authorId = intent.extras?.get(DETAILS_EXTRA_KEY) as Int

        detailsViewModel.postsLiveData.observe(this, {
            when (it) {
                is ViewState.Success -> {
                    Log.i("Authors", "data -> $it.data")
                    setupList(it.data)
                }
                is ViewState.Loading -> {
                    Log.i("Authors", "loading")
                }
                is ViewState.Failure -> {
                    Log.i("Authors", "error")
                }
            }
        })

        detailsViewModel.getPostsByAuthorId(authorId)
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

        private const val DETAILS_EXTRA_KEY = "details_extra_key"

        fun detailActivityIntent(activity: Activity, id: Int) =
            Intent(activity, DetailsActivity::class.java)
                .putExtra(DETAILS_EXTRA_KEY, id)
    }
}
