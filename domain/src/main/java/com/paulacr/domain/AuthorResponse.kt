package com.paulacr.domain

import com.google.gson.annotations.SerializedName

data class AuthorResponse(
    val id: Int,
    val name: String,
    @SerializedName("userName") val username: String,
    val email: String,
    val avatarUrl: String,
    val address: AddressResponse
)

data class AddressResponse(val latitude: Float, val longitude: Float)