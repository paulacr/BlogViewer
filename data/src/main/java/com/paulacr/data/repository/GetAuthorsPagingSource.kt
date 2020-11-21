package com.paulacr.data.repository

import androidx.paging.rxjava2.RxPagingSource
import com.paulacr.data.mapper.AuthorMapper
import com.paulacr.data.network.ApiService
import com.paulacr.domain.Author
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetAuthorsPagingSource @Inject constructor(
    private val api: ApiService,
    private val mapper: AuthorMapper
) :
    RxPagingSource<Int, Author>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Author>> {
        val position = params.key ?: 1

        return api.getAuthors(position)
            .subscribeOn(Schedulers.io())
            .map(mapper::map)
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


}