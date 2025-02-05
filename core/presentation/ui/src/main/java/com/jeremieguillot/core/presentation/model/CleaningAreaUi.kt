package com.jeremieguillot.core.presentation.model

import android.graphics.Bitmap
import com.jeremieguillot.core.domain.AreaId
import com.jeremieguillot.core.domain.CleaningStatus

data class CleaningAreaUi(
    val id: AreaId,
    val formattedDate: String,
    val photos: List<Bitmap>,
    val status: CleaningStatus,
)