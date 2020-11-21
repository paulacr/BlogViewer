package com.paulacr.blogviewer.authors

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulacr.blogviewer.ViewState
import com.paulacr.blogviewer.databinding.ActivityAuthorsListBinding
import com.paulacr.domain.Author
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: AuthorsViewModel by viewModels()
    lateinit var binding: ActivityAuthorsListBinding
    private val adapter = AuthorsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.authorsLiveData.observe(this, {
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
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAuthors()
    }

    private fun setupList(authors: PagingData<Author>) {
        val recyclerView = binding.authorsList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.submitData(lifecycle, authors)
    }
}
