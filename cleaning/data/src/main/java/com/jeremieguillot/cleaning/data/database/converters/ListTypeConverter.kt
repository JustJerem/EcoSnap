package com.jeremieguillot.cleaning.data.database.converters

import androidx.room.TypeConverter

class ListTypeConverter {

    @TypeConverter
    fun fromList(value: List<String>?): String? {
        return value?.joinToString(",")
    }

    @TypeConverter
    fun toList(value: String?): List<String>? {
        return value?.split(",")?.map { it.trim() }
    }
}