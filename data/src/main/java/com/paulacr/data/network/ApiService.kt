package com.paulacr.data.network

import com.paulacr.domain.AuthorRawData
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/authors")
    fun getAuthors(): Single<List<AuthorRawData>>
}