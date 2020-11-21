package com.paulacr.blogviewer.authors

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
import com.paulacr.domain.Author
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: AuthorsViewModel by viewModels()
    lateinit var binding: ActivityAuthorsListBinding
    private val adapter = AuthorsAdapter { author ->
        // todo create intent to detail screen
        Log.i("item clicked", "result -> ${author.id}}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.authorsLiveData.observe(this, {
            when (it) {
                is ViewState.Success -> {
                    Log.i("Authors", "data -> $it.data")

                    if (adapter.itemCount > 0) {
                        adapter.submitData(lifecycle, it.data)
                    } else {
                        setupList(it.data)
                    }
                }
                is ViewState.Loading -> {
                    Log.i("Authors", "loading")
                }
                is ViewState.Failure -> {
                    Log.i("Authors", "error")
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAuthors()
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
