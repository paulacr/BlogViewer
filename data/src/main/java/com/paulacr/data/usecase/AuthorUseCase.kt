package com.paulacr.data.usecase

import androidx.paging.PagingData
import com.paulacr.data.repository.BlogRepository
import com.paulacr.domain.Author
import com.paulacr.domain.Post
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

interface AuthorUseCase {

    fun getAuthor(): Flowable<PagingData<Author>>

    fun getPostsByAuthorId(id: Int): Single<List<Post>>
}

class AuthorUseCaseImpl @Inject constructor(private val repository: BlogRepository) : AuthorUseCase {
    override fun getAuthor(): Flowable<PagingData<Author>> = repository.getAuthors()

    override fun getPostsByAuthorId(id: Int): Single<List<Post>> = repository.getPostsByAuthorId(id)
}