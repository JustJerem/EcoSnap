package com.jeremieguillot.cleaning.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeremieguillot.cleaning.data.database.converters.CleaningStatusConverter
import com.jeremieguillot.cleaning.data.database.converters.ListTypeConverter
import com.jeremieguillot.cleaning.data.database.dao.CleaningAreaDao
import com.jeremieguillot.cleaning.data.database.entities.CleaningAreaEntity

@Database(entities = [CleaningAreaEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListTypeConverter::class, CleaningStatusConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cleaningAreaDao(): CleaningAreaDao
}