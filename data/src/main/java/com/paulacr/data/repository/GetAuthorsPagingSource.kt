package com.paulacr.data.repository

import androidx.paging.rxjava2.RxPagingSource
import com.paulacr.data.common.logError
import com.paulacr.data.mapper.AuthorMapper
import com.paulacr.data.mapper.CommentsMapper
import com.paulacr.data.mapper.PostMapper
import com.paulacr.data.network.ApiService
import com.paulacr.domain.Author
import com.paulacr.domain.Comment
import com.paulacr.domain.Post
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetAuthorsPagingSource @Inject constructor(
    private val api: ApiService,
    private val authorMapper: AuthorMapper,
    private val postsMapper: PostMapper,
    private val commentsMapper: CommentsMapper
) :
    RxPagingSource<Int, Author>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Author>> {
        val position = params.key ?: 1

        return api.getAuthors(position)
            .subscribeOn(Schedulers.io())
            .map(authorMapper::map)
            .map {
                toLoadResult(it, position)
            }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: List<Author>, position: Int): LoadResult<Int, Author> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.size) null else position + 1
        )
    }

    fun getPostsByAuthorId(id: Int): Single<List<Post>> =
        api.getPostsByAuthorId(id)
            .subscribeOn(Schedulers.io())
            .doOnError {
                logError("Error posts", it)
            }
            .map(postsMapper::map)

    fun getCommentsByUsername(username: String): Single<List<Comment>> =
        api.getCommentsByUsername(username)
            .subscribeOn(Schedulers.io())
            .doOnError {
                logError("Error posts", it)
            }
            .map(commentsMapper::map)
}