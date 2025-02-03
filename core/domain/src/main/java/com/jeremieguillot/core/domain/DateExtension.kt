package com.jeremieguillot.core.domain

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun String.toZonedDateTime(): ZonedDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val localDateTime = LocalDateTime.parse(this, formatter)
    return localDateTime.atZone(ZoneId.of("UTC")) // Convert to UTC time zone
}

fun ZonedDateTime.toFormattedString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return this.withZoneSameInstant(ZoneId.of("UTC")).format(formatter)
}

fun ZonedDateTime.toLocalizedDateString(): String {
    return this.format(
        DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.getDefault())
    )
}

fun ZonedDateTime.toUtc(): ZonedDateTime = this.withZoneSameInstant(ZoneId.of("UTC"))