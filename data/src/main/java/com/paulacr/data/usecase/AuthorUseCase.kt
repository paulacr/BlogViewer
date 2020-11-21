package com.paulacr.data.usecase

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.paulacr.data.repository.BlogRepository
import com.paulacr.domain.Author
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

interface AuthorUseCase {

    fun getAuthor(): Flowable<PagingData<Author>>
}

class AuthorUseCaseImpl @Inject constructor(private val repository: BlogRepository) : AuthorUseCase {
    override fun getAuthor(): Flowable<PagingData<Author>> = repository.getAuthors()
}