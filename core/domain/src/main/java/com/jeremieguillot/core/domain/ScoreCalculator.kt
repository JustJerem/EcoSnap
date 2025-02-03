package com.jeremieguillot.core.domain

class ScoreCalculator(
    private val numberOfPhotos: Int,
    private val numberOfParticipants: Int = 0,
    private val isCleaningDone: Boolean = false
) {
    fun calculateScore(): Int {
        val photoPoints = numberOfPhotos * 10
        val participantsPoints = numberOfParticipants * 5
        val cleaningPoints = if (isCleaningDone) 50 else 0
        return photoPoints + participantsPoints + cleaningPoints
    }
}