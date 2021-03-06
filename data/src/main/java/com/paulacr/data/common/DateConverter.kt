package com.paulacr.data.common

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

private const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX"
private const val DATE_YEAR_MONTH_DAY_FORMAT = "yyyy/MM/dd"

fun String.getDateTime(): LocalDateTime {
    return LocalDateTime.parse(this, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.getDefault()))
}

fun LocalDateTime.getYearMonthDayDate(): String? {
    val formatter = DateTimeFormatter.ofPattern(DATE_YEAR_MONTH_DAY_FORMAT, Locale.getDefault())
    return formatter.format(this)
}

