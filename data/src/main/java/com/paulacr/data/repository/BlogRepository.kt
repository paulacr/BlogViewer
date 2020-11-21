package com.paulacr.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.rxjava2.flowable
import com.paulacr.data.mapper.AuthorMapper
import com.paulacr.data.network.ApiService
import com.paulacr.domain.Author
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

interface BlogRepository {

    fun getAuthors(): Flowable<PagingData<Author>>
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
}