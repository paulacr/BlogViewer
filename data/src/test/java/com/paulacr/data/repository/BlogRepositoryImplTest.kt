package com.paulacr.data.repository

import com.paulacr.data.mapper.AuthorMapper
import com.paulacr.data.network.ApiService
import com.paulacr.domain.AddressRawData
import com.paulacr.domain.Author
import com.paulacr.domain.AuthorRawData
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BlogRepositoryImplTest {

    private val authorRawData = mockk<AuthorRawData> {
        every { id } returns 1
        every { name } returns "John"
        every { username } returns "john123"
        every { email } returns "john@email.com"
        every { avatarUrl } returns "https://avatarurl/john"
        every { address } returns AddressRawData(33.33F, 44.44F)
    }

    private val authorsListRawData = listOf(authorRawData)
    private val apiService = mockk<ApiService> {
        every { getAuthors() } returns Single.just(authorsListRawData)
    }

    private var mapper: AuthorMapper = AuthorMapper()
    private lateinit var repository: BlogRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = BlogRepositoryImpl(apiService, mapper)
    }

    @Test
    fun shouldMapAuthorRawDataToAuthor() {

        repository.getAuthors()

        val authorResultList = listOf(Author(1, "John", "https://avatarurl/john"))
        assertEquals(authorResultList, mapper.map(authorsListRawData))
    }
}