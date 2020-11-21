package com.paulacr.data.repository

import com.paulacr.data.mapper.AuthorMapper
import com.paulacr.data.network.ApiService
import com.paulacr.domain.Author
import io.reactivex.Single
import javax.inject.Inject

interface BlogRepository {

    fun getAuthors(): Single<List<Author>>
}

class BlogRepositoryImpl @Inject constructor(private val api: ApiService, private val mapper: AuthorMapper) : BlogRepository {

    override fun getAuthors(): Single<List<Author>> = api.getAuthors().map(mapper::map)
}