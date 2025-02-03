package com.jeremieguillot.cleaning.data.database.converters

import androidx.room.TypeConverter
import com.jeremieguillot.core.domain.CleaningStatus

class CleaningStatusConverter {
    @TypeConverter
    fun fromStatus(status: CleaningStatus): String {
        return status.name
    }

    @TypeConverter
    fun toStatus(value: String): CleaningStatus {
        return CleaningStatus.valueOf(value)
    }
}