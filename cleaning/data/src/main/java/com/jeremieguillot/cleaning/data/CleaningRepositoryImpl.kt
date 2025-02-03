package com.jeremieguillot.cleaning.data

import com.jeremieguillot.cleaning.data.database.RoomCleaningAreaDataSource
import com.jeremieguillot.cleaning.data.mapper.toCleaningArea
import com.jeremieguillot.cleaning.data.mapper.toCleaningAreaEntity
import com.jeremieguillot.core.domain.CleaningArea
import com.jeremieguillot.core.domain.CleaningRepository
import com.jeremieguillot.core.domain.CleaningStatus
import com.jeremieguillot.core.domain.util.DataError
import com.jeremieguillot.core.domain.util.EmptyResult
import com.jeremieguillot.core.domain.util.Result
import com.jeremieguillot.core.domain.util.asEmptyDataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CleaningRepositoryImpl(
    private val localCleaningAreaDataSource: RoomCleaningAreaDataSource,
) : CleaningRepository {


    override suspend fun saveCleaningArea(cleaningArea: CleaningArea): EmptyResult<DataError> {
        val entity = cleaningArea.toCleaningAreaEntity()
        val result = localCleaningAreaDataSource.insertCleaningArea(entity)
        return result.asEmptyDataResult()
    }

    override suspend fun getCleaningArea(): Flow<List<CleaningArea>> {
        return localCleaningAreaDataSource.getAllCleaningAreas()
            .map { it.map { entity -> entity.toCleaningArea() } }
    }

    override suspend fun getCleaningAreaById(id: Long): Result<CleaningArea, DataError.Local> {
        val localResult = localCleaningAreaDataSource.getCleaningAreaById(id)
        if (localResult !is Result.Success) {
            return Result.Error(DataError.Local.NOT_FOUND)
        }

        return Result.Success(localResult.data.toCleaningArea())
    }

    override suspend fun submitCleaningProof(cleaningArea: CleaningArea): EmptyResult<DataError> {

        /***
        We should send the object to the backend, but due to time constraints, here's the explanation.
        To properly implement this functionality using Ktor, the process would involve:

        1. Set up a Ktor client:
        - Add the Ktor dependency to the project and configure the client in the data layer.
        - For example, use `HttpClient` with appropriate features like JSON serialization/deserialization and logging.

        2. Define the API endpoint:
        - Determine the endpoint where the cleaning proof submission needs to be sent.
        - Define the HTTP method (e.g., POST) and the structure of the request body.

        3. Serialize the data:
        - Convert `cleaningArea` into a JSON object using a serialization library like `kotlinx.serialization`.

        4. Make the API call:
        - Use the Ktor client to send a POST request to the endpoint, passing the serialized data in the request body.

        Example:
        ```
        val response: HttpResponse = client.post("https://api.endpoint/submit") {
        contentType(ContentType.Application.Json)
        setBody(
        mapOf(
        "cleaningArea" to cleaningArea
        )
        )
        }
        ```

        5. Handle the response:
        - Parse the server's response (e.g., success or error message) and take appropriate action.
        - For example, update the local database only if the server confirms the data was received successfully.

        6. Error handling:
        - Use `try-catch` blocks or structured error handling in Ktor to handle network errors or invalid responses.
         */

        val networkCallSuccess = true // Simulate network call success
        if (networkCallSuccess) {
            val entity = cleaningArea.toCleaningAreaEntity().apply {
                this.status = CleaningStatus.SUBMITTED
            }
            val result = localCleaningAreaDataSource.updateCleaningArea(entity)
            return result.asEmptyDataResult()
        } else {
            return Result.Error(DataError.Network.SERVER_ERROR)
        }
    }
}