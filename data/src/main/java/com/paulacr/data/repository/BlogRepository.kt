package com.paulacr.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.paulacr.domain.Author
import com.paulacr.domain.Comment
import com.paulacr.domain.Post
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

interface BlogRepository {

    fun getAuthors(): Flowable<PagingData<Author>>

    fun getPostsByAuthorId(id: Int): Single<List<Post>>

    fun getCommentsByPostId(postId: Int): Single<List<Comment>>
}

class BlogRepositoryImpl @Inject constructor(private val pagingSource: GetAuthorsPagingSource) : BlogRepository {

    override fun getAuthors(): Flowable<PagingData<Author>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true,
                maxSize = 25,
                prefetchDistance = 5,
                initialLoadSize = 40),
            pagingSourceFactory = { pagingSource }
        ).flowable

    override fun getPostsByAuthorId(id: Int): Single<List<Post>> = pagingSource.getPostsByAuthorId(id)

    override fun getCommentsByPostId(postId: Int): Single<List<Comment>> = pagingSource.getCommentsByPostId(postId)

}