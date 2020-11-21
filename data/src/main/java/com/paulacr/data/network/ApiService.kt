package com.paulacr.data.network

import com.paulacr.domain.AuthorResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/authors")
    fun getAuthors(
        @Query ("_page") page: Int
    ): Single<List<AuthorResponse>>
}