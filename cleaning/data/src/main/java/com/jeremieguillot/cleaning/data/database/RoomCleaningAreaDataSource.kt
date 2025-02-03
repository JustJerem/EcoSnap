package com.jeremieguillot.cleaning.data.database

import android.database.sqlite.SQLiteFullException
import com.jeremieguillot.cleaning.data.database.dao.CleaningAreaDao
import com.jeremieguillot.cleaning.data.database.entities.CleaningAreaEntity
import com.jeremieguillot.core.domain.AreaId
import com.jeremieguillot.core.domain.util.DataError
import com.jeremieguillot.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

class RoomCleaningAreaDataSource(private val cleaningAreaDao: CleaningAreaDao) {

    suspend fun insertCleaningArea(cleaningArea: CleaningAreaEntity): Result<AreaId, DataError.Local> {
        return try {
            val id = cleaningAreaDao.insert(cleaningArea)
            Result.Success(id)
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    fun getAllCleaningAreas(): Flow<List<CleaningAreaEntity>> {
        return cleaningAreaDao.getAllCleaningAreas()
    }

    suspend fun getCleaningAreaById(id: Long): Result<CleaningAreaEntity, DataError.Local> {
        try {
            val areas = cleaningAreaDao.getCleaningAreaById(id)
                ?: return Result.Error(DataError.Local.NOT_FOUND)
            return Result.Success(areas)
        } catch (e: SQLiteFullException) {
            return Result.Error(DataError.Local.NOT_FOUND)
        }
    }

    suspend fun updateCleaningArea(cleaningArea: CleaningAreaEntity): Result<Boolean, DataError.Local> {
        return try {
            cleaningAreaDao.updateCleaningArea(cleaningArea)
            Result.Success(true)
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }
}
