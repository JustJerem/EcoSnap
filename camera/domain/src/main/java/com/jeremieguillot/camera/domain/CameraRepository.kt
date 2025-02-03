package com.jeremieguillot.camera.domain


interface CameraRepository {
    fun saveBitmapToInternalStorage(
        filesDir: String,
        byteArray: ByteArray,
        fileName: String
    ): String?
}