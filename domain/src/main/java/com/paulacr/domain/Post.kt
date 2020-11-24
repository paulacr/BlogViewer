package com.paulacr.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime

@Parcelize
data class Post(
    val id: Int,
    val date: LocalDateTime,
    val title: String,
    val content: String,
    val postUrl: String,
    val authorId: Int
): Parcelable