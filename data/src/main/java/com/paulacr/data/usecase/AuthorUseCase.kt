package com.paulacr.data.usecase

import com.paulacr.data.repository.BlogRepository
import com.paulacr.domain.Author
import io.reactivex.Single
import javax.inject.Inject

interface AuthorUseCase {

    fun getAuthor(): Single<List<Author>>
}

class AuthorUseCaseImpl @Inject constructor(private val repository: BlogRepository) : AuthorUseCase {
    override fun getAuthor(): Single<List<Author>> = repository.getAuthors()
}