package com.paulacr.data.responsedata

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    val id: Int,
    val date: String,
    @SerializedName("body")
    val comment: String,
    @SerializedName("userName")
    val username: String?
)