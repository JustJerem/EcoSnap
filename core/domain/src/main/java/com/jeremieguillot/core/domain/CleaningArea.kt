package com.jeremieguillot.core.domain


import java.time.ZonedDateTime

typealias AreaId = Long

data class CleaningArea(
    val id: AreaId,
    val dateTimeUtc: ZonedDateTime,
    val photoPaths: List<String>,
    val status: CleaningStatus = CleaningStatus.DRAFT,
    var points: Int = 0,
    var taggedParticipants: List<String> = emptyList(),
    var afterPhoto: String? = null
)