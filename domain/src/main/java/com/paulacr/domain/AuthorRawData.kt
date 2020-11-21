package com.paulacr.domain

import com.google.gson.annotations.SerializedName

data class AuthorRawData(
    val id: Int,
    val name: String,
    @SerializedName("userName") val username: String,
    val email: String,
    val avatarUrl: String,
    val address: AddressRawData
)

data class AddressRawData(val latitude: Float, val longitude: Float)