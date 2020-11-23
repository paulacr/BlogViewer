package com.paulacr.data.repository

import com.paulacr.data.AuthorCache
import com.paulacr.data.AuthorCacheImpl
import com.paulacr.data.mapper.AuthorMapper
import com.paulacr.data.mapper.PostMapper
import com.paulacr.data.network.ApiService
import com.paulacr.data.usecase.AuthorUseCase
import com.paulacr.data.usecase.AuthorUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    fun provideAuthorUseCase(repository: BlogRepository): AuthorUseCase =
        AuthorUseCaseImpl(repository)

    @Provides
    fun provideAuthorRepository(pagingSource: GetAuthorsPagingSource): BlogRepository =
        BlogRepositoryImpl(pagingSource)

    @Provides
    fun providePagingSource(apiService: ApiService, authorMapper: AuthorMapper, postMapper: PostMapper, cache: AuthorCache): GetAuthorsPagingSource =
        GetAuthorsPagingSource(apiService, authorMapper, postMapper, cache)

    @Provides
    fun provideAuthorCache(): AuthorCache {
        return AuthorCacheImpl()
    }
}