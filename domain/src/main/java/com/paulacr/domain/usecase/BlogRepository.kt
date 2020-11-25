package com.paulacr.domain.usecase

import androidx.paging.PagingData
import com.paulacr.domain.Author
import com.paulacr.domain.Comment
import com.paulacr.domain.Post
import io.reactivex.Flowable
import io.reactivex.Single

interface BlogRepository {

    fun getAuthors(): Flowable<PagingData<Author>>

    fun getPostsByAuthorId(id: Int): Single<List<Post>>

    fun getCommentsByPostId(postId: Int): Single<List<Comment>>
}