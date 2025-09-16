package com.jeremieguillot.core.domain.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class ResultExtensionsTest {

    private object FakeError : Error

    @Test
    fun `map transforms success value`() {
        val initial: Result<Int, FakeError> = Result.Success(2)

        val mapped = initial.map { it * 3 }

        assertTrue(mapped is Result.Success)
        assertEquals(6, (mapped as Result.Success).data)
    }

    @Test
    fun `map keeps error untouched`() {
        val errorInstance = FakeError
        val initial: Result<Int, FakeError> = Result.Error(errorInstance)

        val mapped = initial.map { it * 3 }

        assertTrue(mapped is Result.Error)
        assertSame(errorInstance, (mapped as Result.Error).error)
    }

    @Test
    fun `asEmptyDataResult wraps success into unit`() {
        val initial: Result<String, FakeError> = Result.Success("value")

        val emptyResult = initial.asEmptyDataResult()

        assertTrue(emptyResult is Result.Success)
        assertEquals(Unit, (emptyResult as Result.Success).data)
    }

    @Test
    fun `asEmptyDataResult propagates error`() {
        val errorInstance = FakeError
        val initial: Result<String, FakeError> = Result.Error(errorInstance)

        val emptyResult = initial.asEmptyDataResult()

        assertTrue(emptyResult is Result.Error)
        assertSame(errorInstance, (emptyResult as Result.Error).error)
    }
}
