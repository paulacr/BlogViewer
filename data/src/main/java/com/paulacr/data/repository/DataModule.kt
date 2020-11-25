package com.paulacr.data.repository

import com.paulacr.data.mapper.AuthorMapper
import com.paulacr.data.mapper.CommentsMapper
import com.paulacr.data.mapper.PostMapper
import com.paulacr.data.network.ApiService
import com.paulacr.domain.usecase.BlogRepository
import com.paulacr.domain.usecase.PostsUseCase
import com.paulacr.domain.usecase.PostsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    fun provideAuthorUseCase(repository: BlogRepository): PostsUseCase =
        PostsUseCaseImpl(repository)

    @Provides
    fun provideAuthorRepository(pagingSource: GetAuthorsPagingSource): BlogRepository =
        BlogRepositoryImpl(pagingSource)

    @Provides
    fun providePagingSource(apiService: ApiService, authorMapper: AuthorMapper, postMapper: PostMapper, commentsMapper: CommentsMapper): GetAuthorsPagingSource =
        GetAuthorsPagingSource(apiService, authorMapper, postMapper, commentsMapper)
}