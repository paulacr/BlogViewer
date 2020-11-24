package com.paulacr.data.repository

import androidx.paging.PagingSource
import com.paulacr.data.mapper.AuthorMapper
import com.paulacr.data.mapper.CommentsMapper
import com.paulacr.data.mapper.PostMapper
import com.paulacr.data.network.ApiService
import com.paulacr.domain.*
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDateTime

class GetAuthorsPagingTest {

    private val authorsResponse = listOf(
        AuthorResponse(
            1,
            "John",
            "john123",
            "john@email.com",
            "https://avatarurl/john",
            AddressResponse(33.33F, 44.44F)
        )
    )

    private val postsResponse = listOf(
        PostResponse(
            2,
            "2017-01-29T23:53:33.320Z",
            "Post title",
            "some post description, post text here",
            "https://posturl/john",
            1
        )
    )

    private val commentsResponse = listOf(
        CommentResponse(
            3,
            "2017-01-29T23:53:33.320Z",
            "This is a comment in the post",
            "paula"
        )
    )

    private val apiService = mockk<ApiService> {
        every { getPostsByAuthorId(1) } returns Single.just(postsResponse)
        every { getCommentsByPostId(1) } returns Single.just(commentsResponse)
    }

    private var authorMapper: AuthorMapper = AuthorMapper()
    private var postsMapper: PostMapper = PostMapper()
    private var commentsMapper: CommentsMapper = CommentsMapper()
    private var pagingSource =
        GetAuthorsPagingSource(apiService, authorMapper, postsMapper, commentsMapper)

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

    @Test
    fun shouldMapCommentsResponseToComments() {
        pagingSource.getCommentsByPostId(1)

        val commentsResult = listOf(
            Comment(
                3,
                "2017/01/29",
                "This is a comment in the post",
                "paula"
            )
        )
        assertEquals(commentsResult, commentsMapper.map(commentsResponse))
        assertEquals(1, commentsResult.size)
    }
}