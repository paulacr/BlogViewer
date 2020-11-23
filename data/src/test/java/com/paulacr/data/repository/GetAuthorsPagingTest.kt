package com.paulacr.data.repository

import androidx.paging.PagingSource
import com.paulacr.data.mapper.AuthorMapper
import com.paulacr.data.mapper.PostMapper
import com.paulacr.data.network.ApiService
import com.paulacr.domain.AddressResponse
import com.paulacr.domain.AuthorResponse
import com.paulacr.domain.Post
import com.paulacr.domain.PostResponse
import com.paulacr.domain.Author
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class GetAuthorsPagingTest {

    private val authorsResponse = listOf(mockk<AuthorResponse> {
        every { id } returns 1
        every { name } returns "John"
        every { username } returns "john123"
        every { email } returns "john@email.com"
        every { avatarUrl } returns "https://avatarurl/john"
        every { address } returns AddressResponse(33.33F, 44.44F)
    })

    private val postsResponse = listOf(mockk<PostResponse> {
        every { id } returns 2
        every { date } returns "2017-01-29T23:53:33.320Z"
        every { title } returns "Post title"
        every { content } returns "some post description, post text here"
        every { imageUrl } returns "https://posturl/john"
        every { authorId } returns 1
    })

    private val apiService = mockk<ApiService> {
        every { getPostsByAuthorId(1) } returns Single.just(postsResponse)
    }

    private var authorMapper: AuthorMapper = AuthorMapper()
    private var postsMapper: PostMapper = PostMapper()
    private var pagingSource = GetAuthorsPagingSource(apiService, authorMapper, postsMapper)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun shouldMapPostsResponseToPosts() {
        pagingSource.getPostsByAuthorId(1)

        val postResult = listOf(
            Post(
                2,
                LocalDateTime.parse("2017-01-29T23:53:33.320"),
                "Post title",
                "some post description, post text here",
                "https://posturl/john",
                1
            )
        )
        assertEquals(postResult, postsMapper.map(postsResponse))
        assertEquals(1, postResult.size)
    }

    @Test
    fun shouldMapAuthorsResponseToAuthors() {
        every { apiService.getAuthors(1) } returns Single.just(authorsResponse)
        val params: PagingSource.LoadParams<Int> = mockk(relaxed = true) {
            every { key } returns 1
        }
        pagingSource.loadSingle(params)

        val authorResult = listOf(Author(1, "John", "john123", "https://avatarurl/john"))

        assertEquals(authorResult, authorMapper.map(authorsResponse))
        assertEquals(1, authorResult.size)
    }
}