package com.jeremieguillot.camera.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeremieguillot.camera.domain.CameraRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.UUID

class CameraViewModel(
    private val repository: CameraRepository
) : ViewModel() {

    private val eventChannel = Channel<CameraContract.Event>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: CameraContract.Action) {
        when (action) {
            is CameraContract.Action.TakePhoto -> {
                val fileName = "picture_${UUID.randomUUID()}.png"
                val path =
                    repository.saveBitmapToInternalStorage(action.fileDir, action.bytes, fileName)
                if (path != null) {
                    viewModelScope.launch {
                        eventChannel.send(CameraContract.Event.ReturnPhotoPath(path))
                    }
                }
            }
        }
    }
}
