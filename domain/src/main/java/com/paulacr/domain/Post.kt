package com.paulacr.domain

import org.threeten.bp.LocalDateTime

data class Post(
    val id: Int,
    val date: LocalDateTime,
    val title: String,
    val content: String,
    val postUrl: String,
    val authorId: Int)