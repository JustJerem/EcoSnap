package com.jeremieguillot.camera.presentation

sealed class CameraContract {

    sealed class Action {
        data class TakePhoto(val bytes: ByteArray, val fileDir: String) : Action()
    }

    sealed class Event {
        data class ReturnPhotoPath(val path: String) : Event()
    }
}