package com.paulacr.blogviewer.authors

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulacr.blogviewer.ViewState
import com.paulacr.blogviewer.databinding.ActivityAuthorsListBinding
import com.paulacr.domain.Author
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: AuthorsViewModel by viewModels()
    lateinit var binding: ActivityAuthorsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getAuthors()

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

    private fun setupList(authors: List<Author>) {
        val recyclerView = binding.authorsList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AuthorsAdapter(authors, { position, author ->
        })
    }
}
