package com.paulacr.data.common

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

private const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX"

fun String.getDateTime(): LocalDateTime {
    return LocalDateTime.parse(this, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.getDefault()))
}
