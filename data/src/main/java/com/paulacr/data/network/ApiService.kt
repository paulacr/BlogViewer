package com.paulacr.data.network

import com.paulacr.domain.AuthorResponse
import com.paulacr.domain.CommentResponse
import com.paulacr.domain.PostResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/authors")
    fun getAuthors(
        @Query ("_page") page: Int
    ): Single<List<AuthorResponse>>

    @GET("/posts")
    fun getPostsByAuthorId(
        @Query ("authorId") authorId: Int
    ): Single<List<PostResponse>>

    @GET("/comments")
    fun getCommentsByUsername(
        @Query ("username") username: String
    ): Single<List<CommentResponse>>
}