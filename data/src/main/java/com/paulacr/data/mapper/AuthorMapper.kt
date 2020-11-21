package com.paulacr.data.mapper

import com.paulacr.domain.Author
import com.paulacr.domain.AuthorResponse
import javax.inject.Inject

class AuthorMapper @Inject constructor() {

    fun map(rawData: List<AuthorResponse>): MutableList<Author> {
        val listAuthors = mutableListOf<Author>()

        rawData.forEach {
            listAuthors.add(Author(
                id = it.id,
                name = it.name,
                username = it.username,
                avatarUrl = it.avatarUrl
            ))
        }

        return listAuthors
    }

}