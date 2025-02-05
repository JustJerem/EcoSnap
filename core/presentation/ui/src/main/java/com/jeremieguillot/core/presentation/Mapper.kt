package com.jeremieguillot.core.presentation

import com.jeremieguillot.core.domain.CleaningArea
import com.jeremieguillot.core.domain.toLocalizedDateString
import com.jeremieguillot.core.presentation.model.CleaningAreaUi
import com.jeremieguillot.core.presentation.ui.loadBitmapFromPath

fun CleaningArea.toUiModel(): CleaningAreaUi {
    return CleaningAreaUi(
        id = id,
        formattedDate = dateTimeUtc.toLocalizedDateString(),
        photos = photoPaths.mapNotNull { path -> loadBitmapFromPath(path) },
        status = status,
    )
}