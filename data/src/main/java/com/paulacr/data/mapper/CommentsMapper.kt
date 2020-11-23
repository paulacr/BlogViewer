package com.paulacr.data.mapper

import com.paulacr.data.common.getDateTime
import com.paulacr.domain.Comment
import com.paulacr.domain.CommentResponse
import javax.inject.Inject

class CommentsMapper @Inject constructor() {

    fun map(rawData: List<CommentResponse>): List<Comment> =

        rawData.map {
            Comment(
                id = it.id,
                date = getDate(it.date),
                comment = it.comment,
                username = it.username
            )
        }

    private fun getDate(notFormattedDate: String) = notFormattedDate.getDateTime()
}

