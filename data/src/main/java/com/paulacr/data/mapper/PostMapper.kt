package com.paulacr.data.mapper

import com.paulacr.data.common.getDateTime
import com.paulacr.domain.Post
import com.paulacr.data.responsedata.PostResponse
import javax.inject.Inject

class PostMapper @Inject constructor() {

    fun map(rawData: List<PostResponse>): List<Post> =
        rawData.map {
            Post(
                id = it.id,
                date = getDate(it.date),
                title = it.title,
                content = it.content,
                postUrl = it.imageUrl,
                authorId = it.authorId
            )
        }

    private fun getDate(notFormattedDate: String) = notFormattedDate.getDateTime()

}