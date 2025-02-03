package com.jeremieguillot.core.domain

import com.jeremieguillot.core.domain.util.DataError
import com.jeremieguillot.core.domain.util.EmptyResult
import com.jeremieguillot.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface CleaningRepository {
    suspend fun saveCleaningArea(cleaningArea: CleaningArea): EmptyResult<DataError>
    suspend fun getCleaningArea(): Flow<List<CleaningArea>>
    suspend fun getCleaningAreaById(id: Long): Result<CleaningArea, DataError.Local>
    suspend fun submitCleaningProof(cleaningArea: CleaningArea): EmptyResult<DataError>
}
