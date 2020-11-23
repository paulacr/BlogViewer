package com.paulacr.data

import com.paulacr.domain.Author
import okhttp3.internal.toImmutableList
import javax.inject.Inject

interface AuthorCache {

    fun saveAuthorData(data: List<Author>)

    fun getAuthorData(): List<Author>

}
class AuthorCacheImpl @Inject constructor() : AuthorCache {

    private val cacheData: MutableList<Author> = mutableListOf()

    override fun saveAuthorData(data: List<Author>) {
        cacheData.clear()
        cacheData.addAll(data)
    }

    override fun getAuthorData(): List<Author> {
        return cacheData.toImmutableList()
    }
}