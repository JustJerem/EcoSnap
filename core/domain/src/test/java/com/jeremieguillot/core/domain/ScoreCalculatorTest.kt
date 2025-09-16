package com.jeremieguillot.core.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class ScoreCalculatorTest {

    @Test
    fun `calculateScore returns zero when nothing is provided`() {
        val calculator = ScoreCalculator(numberOfPhotos = 0, numberOfParticipants = 0, isCleaningDone = false)

        val score = calculator.calculateScore()

        assertEquals(0, score)
    }

    @Test
    fun `calculateScore adds photo and participant points`() {
        val calculator = ScoreCalculator(numberOfPhotos = 3, numberOfParticipants = 2, isCleaningDone = false)

        val score = calculator.calculateScore()

        assertEquals(40, score)
    }

    @Test
    fun `calculateScore adds cleaning bonus when cleaning is done`() {
        val calculator = ScoreCalculator(numberOfPhotos = 1, numberOfParticipants = 0, isCleaningDone = true)

        val score = calculator.calculateScore()

        assertEquals(60, score)
    }
}
