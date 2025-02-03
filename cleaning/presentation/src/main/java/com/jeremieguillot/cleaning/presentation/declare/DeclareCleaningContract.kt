package com.jeremieguillot.cleaning.presentation.declare

import com.jeremieguillot.core.presentation.ui.UiText

/**
 * Contract for the Cleaning screen.
 */
sealed class DeclareCleaningContract {

    /**
     * Represents the possible states of the screen.
     */
    data class State(
        val isLoading: Boolean = false,
        val points: Int = 0,
        val currentMission: Int = 1,
        val totalMissions: Int = 2,
        val isNextButtonEnabled: Boolean = false,
        val isCameraButtonEnabled: Boolean = true,
        val imagesPath: List<String> = emptyList(),
        val errorMessage: String? = null
    )

    /**
     * Represents user-initiated actions or intents.
     */
    sealed class Action {
        data object NavigateToTakePhoto : Action()
        data class RemoveImage(val path: String) : Action()
        data class AddImage(val path: String?) : Action()
        data object ValidateCleaningArea : Action()
        data object Back : Action()
    }

    /**
     * Represents one-time events sent from the ViewModel to the UI.
     */
    sealed class Event {
        data object ShowToastAndBackHome : Event()
        data class ShowError(val message: UiText) : Event()
    }
}