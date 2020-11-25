package com.paulacr.domain

data class Comment(
    val id: Int,
    val date: String,
    val comment: String,
    val username: String?
)