package com.paulacr.blogviewer.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paulacr.blogviewer.databinding.ActivityDetailBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {

        private const val DETAILS_EXTRA_KEY = "details_extra_key"

        fun detailActivityIntent(activity: Activity, id: Int) =
            Intent(activity, DetailsActivity::class.java)
                .putExtra(DETAILS_EXTRA_KEY, id)
    }
}