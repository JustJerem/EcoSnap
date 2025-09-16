package com.jeremieguillot.core.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Locale

class DateExtensionTest {

    @Test
    fun `toZonedDateTime parses a utc date string`() {
        val originalLocale = Locale.getDefault()
        Locale.setDefault(Locale.US)

        try {
            val result = "2024-01-02 15:30:00".toZonedDateTime()

            assertEquals(ZoneId.of("UTC"), result.zone)
            assertEquals(2024, result.year)
            assertEquals(1, result.monthValue)
            assertEquals(2, result.dayOfMonth)
            assertEquals(15, result.hour)
            assertEquals(30, result.minute)
        } finally {
            Locale.setDefault(originalLocale)
        }
    }

    @Test
    fun `toFormattedString normalizes date to utc`() {
        val originalLocale = Locale.getDefault()
        Locale.setDefault(Locale.US)

        try {
            val parisDateTime = ZonedDateTime.of(
                LocalDateTime.of(2024, 1, 2, 10, 30, 0),
                ZoneId.of("Europe/Paris")
            )

            val formatted = parisDateTime.toFormattedString()

            assertEquals("2024-01-02 09:30:00", formatted)
        } finally {
            Locale.setDefault(originalLocale)
        }
    }

    @Test
    fun `toLocalizedDateString returns formatted date for current locale`() {
        val originalLocale = Locale.getDefault()
        Locale.setDefault(Locale.US)

        try {
            val dateTime = ZonedDateTime.of(LocalDateTime.of(2024, 1, 2, 0, 0), ZoneId.of("UTC"))

            val localized = dateTime.toLocalizedDateString()

            assertEquals("January 2, 2024", localized)
        } finally {
            Locale.setDefault(originalLocale)
        }
    }

    @Test
    fun `toUtc converts any zone to utc while preserving instant`() {
        val parisDateTime = ZonedDateTime.of(
            LocalDateTime.of(2024, 1, 2, 10, 30),
            ZoneId.of("Europe/Paris")
        )

        val utcDateTime = parisDateTime.toUtc()

        assertEquals(ZoneId.of("UTC"), utcDateTime.zone)
        assertTrue(parisDateTime.toInstant() == utcDateTime.toInstant())
    }
}
