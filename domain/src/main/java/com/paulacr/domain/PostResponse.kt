package com.paulacr.domain

import com.google.gson.annotations.SerializedName

data class PostResponse(
    val id: Int,
    val date: String,
    val title: String,
    @SerializedName("body") val content: String,
    val imageUrl: String,
    val authorId: Int)