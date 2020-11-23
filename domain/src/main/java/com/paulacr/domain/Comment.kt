package com.paulacr.domain

import org.threeten.bp.LocalDateTime

data class Comment(
    val id: Int,
    val date: LocalDateTime,
    val comment: String,
    val username: String
)