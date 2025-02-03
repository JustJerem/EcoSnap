package com.jeremieguillot.cleaning.data.mapper


import com.jeremieguillot.cleaning.data.database.entities.CleaningAreaEntity
import com.jeremieguillot.core.domain.CleaningArea
import com.jeremieguillot.core.domain.toFormattedString
import com.jeremieguillot.core.domain.toZonedDateTime

fun CleaningAreaEntity.toCleaningArea(): CleaningArea {
    return CleaningArea(
        id = id,
        dateTimeUtc = date.toZonedDateTime(),
        photoPaths = photoPaths,
        status = status,
        points = points,
        taggedParticipants = taggedParticipants,
        afterPhoto = afterPhoto
    )
}

fun CleaningArea.toCleaningAreaEntity(): CleaningAreaEntity {
    return CleaningAreaEntity(
        id = id,
        date = dateTimeUtc.toFormattedString(),
        photoPaths = photoPaths,
        status = status,
        points = points,
        taggedParticipants = taggedParticipants,
        afterPhoto = afterPhoto
    )
}
