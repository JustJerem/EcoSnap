package com.jeremieguillot.cleaning.presentation.declare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeremieguillot.core.domain.CleaningArea
import com.jeremieguillot.core.domain.CleaningRepository
import com.jeremieguillot.core.domain.CleaningStatus
import com.jeremieguillot.core.domain.Constants
import com.jeremieguillot.core.domain.ScoreCalculator
import com.jeremieguillot.core.domain.toUtc
import com.jeremieguillot.core.domain.util.Result
import com.jeremieguillot.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class DeclareCleaningViewModel(
    private val cleaningRepository: CleaningRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(DeclareCleaningContract.State())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<DeclareCleaningContract.Event>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: DeclareCleaningContract.Action) {
        when (action) {
            is DeclareCleaningContract.Action.RemoveImage -> {
                _state.update {
                    it.copy(
                        imagesPath = _state.value.imagesPath - action.path
                    )
                }
                updateButtonsState()
                updatePoints()
            }

            is DeclareCleaningContract.Action.AddImage -> {
                if (action.path != null) {
                    _state.update {
                        val updatedPaths = (_state.value.imagesPath + action.path).distinct()
                        it.copy(imagesPath = updatedPaths)
                    }
                    updateButtonsState()
                    updatePoints()
                }
            }

            is DeclareCleaningContract.Action.ValidateCleaningArea -> {
                val cleaningArea = CleaningArea(
                    id = 0,
                    dateTimeUtc = ZonedDateTime.now().toUtc(),
                    photoPaths = state.value.imagesPath,
                    status = CleaningStatus.IN_PROGRESS
                )
                viewModelScope.launch {
                    when (val result = cleaningRepository.saveCleaningArea(cleaningArea)) {
                        is Result.Error -> eventChannel.send(
                            DeclareCleaningContract.Event.ShowError(
                                result.error.asUiText()
                            )
                        )

                        is Result.Success -> eventChannel.send(DeclareCleaningContract.Event.ShowToastAndBackHome)
                    }
                }
            }

            else -> Unit
        }
    }

    private fun updatePoints() {
        val updatedPoints = ScoreCalculator(state.value.imagesPath.size).calculateScore()
        _state.update { it.copy(points = updatedPoints) }
    }

    private fun updateButtonsState() {
        _state.update {
            it.copy(
                isNextButtonEnabled = state.value.imagesPath.isNotEmpty(),
                isCameraButtonEnabled = state.value.imagesPath.size <= Constants.MAX_IMAGE_COUNT
            )
        }
    }
}