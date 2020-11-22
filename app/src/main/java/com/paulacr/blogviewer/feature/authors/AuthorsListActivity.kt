package com.paulacr.blogviewer.feature.authors

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulacr.blogviewer.ViewState
import com.paulacr.blogviewer.databinding.ActivityAuthorsListBinding
import com.paulacr.blogviewer.feature.posts.PostsActivity
import com.paulacr.domain.Author
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorsListActivity : AppCompatActivity() {

    private val listViewModel: AuthorsListViewModel by viewModels()
    lateinit var binding: ActivityAuthorsListBinding
    private val adapter = AuthorsListAdapter { author ->
        startActivity(PostsActivity.detailActivityIntent(this, author))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeAuthorsLiveData()
    }

    override fun onResume() {
        super.onResume()
        listViewModel.getAuthors()
    }

    private fun observeAuthorsLiveData() {
        listViewModel.authorsLiveData.observe(this, {
            when (it) {
                is ViewState.Success -> {
                    binding.loadingView.visibility = View.GONE
                    binding.authorsList.visibility = View.VISIBLE
                    binding.errorState.visibility = View.GONE
                    Log.i("Authors", "data -> $it.data")

                    if (adapter.itemCount > 0) {
                        adapter.submitData(lifecycle, it.data)
                    } else {
                        setupList(it.data)
                    }
                }
                is ViewState.Loading -> {
                    binding.loadingView.visibility = View.VISIBLE
                    binding.authorsList.visibility = View.GONE
                    binding.errorState.visibility = View.GONE
                    Log.i("Authors", "loading")
                }
                is ViewState.Failure -> {
                    binding.loadingView.visibility = View.GONE
                    binding.authorsList.visibility = View.GONE
                    binding.errorState.visibility = View.VISIBLE
                    Log.i("Authors", "error")
                }
            }
        })
    }

    private fun setupList(authors: PagingData<Author>) {
        val recyclerView = binding.authorsList
        val layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(dividerItemDecoration)

        adapter.submitData(lifecycle, authors)
        adapter.addLoadStateListener {
            if (it.source.append is LoadState.Loading) {
                binding.loadingItemsView.visibility = View.VISIBLE
            } else {
                binding.loadingItemsView.visibility = View.INVISIBLE
            }
        }
    }
}
