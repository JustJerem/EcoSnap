package com.jeremieguillot.core.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class CleaningScoreCalculatorTest {

    @Test
    fun `should return zero when no photos, participants, or cleaning are provided`() {
        // Arrange
        val calculator =
            ScoreCalculator(numberOfPhotos = 0, numberOfParticipants = 0, isCleaningDone = false)

        // Act
        val score = calculator.calculateScore()

        // Assert
        assertEquals(0, score)
    }

    @Test
    fun `should calculate score for photos only`() {
        // Arrange
        val calculator = ScoreCalculator(numberOfPhotos = 2)

        // Act
        val score = calculator.calculateScore()

        // Assert
        assertEquals(20, score)
    }

    @Test
    fun `should calculate score for photos and participants`() {
        // Arrange
        val calculator = ScoreCalculator(numberOfPhotos = 2, numberOfParticipants = 3)

        // Act
        val score = calculator.calculateScore()

        // Assert
        assertEquals(35, score)
    }

    @Test
    fun `should calculate score for photos, participants, and cleaning done`() {
        // Arrange
        val calculator = ScoreCalculator(
            numberOfPhotos = 2,
            numberOfParticipants = 3,
            isCleaningDone = true
        )

        // Act
        val score = calculator.calculateScore()

        // Assert
        assertEquals(85, score)
    }

    @Test
    fun `should calculate score correctly for edge case of no participants and cleaning done`() {
        // Arrange
        val calculator = ScoreCalculator(
            numberOfPhotos = 0,
            numberOfParticipants = 0,
            isCleaningDone = true
        )

        // Act
        val score = calculator.calculateScore()

        // Assert
        assertEquals(50, score)
    }
}