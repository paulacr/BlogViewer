package com.paulacr.data.mapper

import com.paulacr.data.common.getDateTime
import com.paulacr.domain.Author
import com.paulacr.domain.AuthorResponse
import com.paulacr.domain.Post
import com.paulacr.domain.PostResponse
import javax.inject.Inject

class PostMapper @Inject constructor() {

    fun map(rawData: List<PostResponse>): MutableList<Post> {
        val postList = mutableListOf<Post>()

        rawData.forEach {
            postList.add(Post(
                id = it.id,
                date = getDate(it.date),
                title = it.title,
                content = it.content,
                postUrl = it.imageUrl,
                authorId = it.authorId
            ))
        }

        return postList
    }

    private fun getDate(notFormattedDate: String) = notFormattedDate.getDateTime()

}