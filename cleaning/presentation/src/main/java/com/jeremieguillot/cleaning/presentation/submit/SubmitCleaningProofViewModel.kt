package com.jeremieguillot.cleaning.presentation.submit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeremieguillot.core.domain.CleaningRepository
import com.jeremieguillot.core.domain.ScoreCalculator
import com.jeremieguillot.core.domain.util.Result
import com.jeremieguillot.core.presentation.toUiModel
import com.jeremieguillot.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SubmitCleaningProofViewModel(
    private val cleaningRepository: CleaningRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SubmitCleaningProofContract.State())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<SubmitCleaningProofContract.Event>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: SubmitCleaningProofContract.Action) {
        when (action) {
            is SubmitCleaningProofContract.Action.Load -> {
                viewModelScope.launch {
                    when (val result = cleaningRepository.getCleaningAreaById(action.id)) {
                        is Result.Error -> eventChannel.send(
                            SubmitCleaningProofContract.Event.ShowError(
                                result.error.asUiText()
                            )
                        )

                        is Result.Success -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    cleaningArea = result.data.toUiModel(),
                                )
                            }
                            updatePoints()
                        }
                    }

                }
            }

            is SubmitCleaningProofContract.Action.AddImage -> {
                _state.value = _state.value.copy(afterPhoto = action.path)
                updatePoints()
            }

            is SubmitCleaningProofContract.Action.AddParticipant -> {
                _state.update {
                    it.copy(
                        taggedParticipants = state.value.taggedParticipants + action.participant
                    )
                }
                updatePoints()
            }

            is SubmitCleaningProofContract.Action.RemoveParticipant -> {
                _state.update { it.copy(taggedParticipants = state.value.taggedParticipants - action.participant) }
            }

            SubmitCleaningProofContract.Action.SubmitProof -> {
                viewModelScope.launch {
                    val id = state.value.cleaningArea?.id ?: return@launch
                    val result = cleaningRepository.getCleaningAreaById(id)

                    if (result is Result.Error) {
                        eventChannel.send(SubmitCleaningProofContract.Event.ShowError(result.error.asUiText()))
                    }

                    val area = (result as Result.Success).data.copy(
                        points = state.value.points,
                        taggedParticipants = state.value.taggedParticipants,
                        afterPhoto = state.value.afterPhoto
                    )

                    val submissionResult = cleaningRepository.submitCleaningProof(area)

                    val event = when (submissionResult) {
                        is Result.Error -> SubmitCleaningProofContract.Event.ShowError(
                            submissionResult.error.asUiText()
                        )

                        is Result.Success -> SubmitCleaningProofContract.Event.ProofSubmitted
                    }

                    eventChannel.send(event)
                }
            }

            else -> Unit
        }
    }

    private fun updatePoints() {
        val updatedPoints = ScoreCalculator(
            numberOfPhotos = state.value.cleaningArea?.photos?.size ?: 0,
            numberOfParticipants = state.value.taggedParticipants.size,
            isCleaningDone = state.value.afterPhoto?.isNotEmpty() == true
        ).calculateScore()
        _state.update { it.copy(points = updatedPoints) }
    }
}

