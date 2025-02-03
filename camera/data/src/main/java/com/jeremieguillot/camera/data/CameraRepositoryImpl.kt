package com.jeremieguillot.camera.data

import com.jeremieguillot.camera.domain.CameraRepository
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class CameraRepositoryImpl : CameraRepository {


    override fun saveBitmapToInternalStorage(
        filesDir: String,
        byteArray: ByteArray,
        fileName: String
    ): String? {
        return try {
            val file = File(filesDir, fileName)

            FileOutputStream(file).use { outputStream ->
                outputStream.write(byteArray)
            }

            file.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}