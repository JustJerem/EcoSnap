package com.jeremieguillot.cleaning.presentation.submit

import com.jeremieguillot.core.presentation.model.CleaningAreaUi
import com.jeremieguillot.core.presentation.ui.UiText

sealed class SubmitCleaningProofContract {
    sealed class Action {
        data class AddImage(val path: String) : Action()
        data class AddParticipant(val participant: String) : Action()
        data class RemoveParticipant(val participant: String) : Action()
        data class Load(val id: Long) : Action()

        data object NavigateBack : Action()
        data object NavigateToTakePhoto : Action()
        data object SubmitProof : Action()
    }

    sealed class Event {
        data object ProofSubmitted : Event()
        data class ShowError(val message: UiText) : Event()
    }

    data class State(
        val isLoading: Boolean = true,
        val cleaningArea: CleaningAreaUi? = null,
        val afterPhoto: String? = null,
        val points: Int = 0,
        val taggedParticipants: List<String> = emptyList(),
        val description: String = "",
        val isSubmitting: Boolean = false,
        val isSuccess: Boolean = false,
        val errorMessage: String? = null
    )
}