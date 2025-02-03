package com.jeremieguillot.cleaning.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jeremieguillot.cleaning.data.database.converters.CleaningStatusConverter
import com.jeremieguillot.cleaning.data.database.converters.ListTypeConverter
import com.jeremieguillot.core.domain.CleaningStatus

@Entity(tableName = "cleaning_areas")
@TypeConverters(ListTypeConverter::class, CleaningStatusConverter::class)
data class CleaningAreaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,
    val photoPaths: List<String>,
    var status: CleaningStatus = CleaningStatus.DRAFT,
    var points: Int = 0,
    var taggedParticipants: List<String> = emptyList(),
    var afterPhoto: String? = null
)