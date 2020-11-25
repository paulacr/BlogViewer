package com.paulacr.data.mapper

import com.paulacr.domain.Author
import com.paulacr.data.responsedata.AuthorResponse
import javax.inject.Inject

class AuthorMapper @Inject constructor() {

    fun map(rawData: List<AuthorResponse>): List<Author> =

        rawData.map {
            Author(
                id = it.id,
                name = it.name,
                username = it.username,
                avatarUrl = it.avatarUrl
            )
        }
}
