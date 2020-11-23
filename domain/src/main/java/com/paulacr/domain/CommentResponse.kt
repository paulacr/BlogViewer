package com.paulacr.domain

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    val id: Int,
    val date: String,
    @SerializedName("body")
    val comment: String,
    val username: String
)