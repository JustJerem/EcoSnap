package com.jeremieguillot.cleaning.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jeremieguillot.cleaning.data.database.entities.CleaningAreaEntity
import com.jeremieguillot.core.domain.CleaningStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface CleaningAreaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cleaningAreaEntity: CleaningAreaEntity): Long

    @Query("SELECT * FROM cleaning_areas ORDER BY date DESC")
    fun getAllCleaningAreas(): Flow<List<CleaningAreaEntity>>

    @Query("SELECT * FROM cleaning_areas WHERE id = :id")
    suspend fun getCleaningAreaById(id: Long): CleaningAreaEntity?

    @Query("UPDATE cleaning_areas SET status = :newStatus WHERE id = :id")
    suspend fun updateStatus(id: Long, newStatus: CleaningStatus)

    @Update
    suspend fun updateCleaningArea(cleaningArea: CleaningAreaEntity)
}