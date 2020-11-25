package com.paulacr.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(
    val id: Int,
    val name: String,
    val username: String,
    val avatarUrl: String
): Parcelable