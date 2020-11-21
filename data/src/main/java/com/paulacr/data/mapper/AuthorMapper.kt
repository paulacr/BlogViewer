package com.paulacr.data.mapper

import com.paulacr.domain.Author
import com.paulacr.domain.AuthorRawData
import javax.inject.Inject

class AuthorMapper @Inject constructor() {

    fun map(rawData: List<AuthorRawData>): MutableList<Author> {
        val listAuthors = mutableListOf<Author>()

        rawData.forEach {
            listAuthors.add(Author(
                id = it.id,
                name = it.name,
                avatarUrl = it.avatarUrl
            ))
        }

        return listAuthors
    }

}