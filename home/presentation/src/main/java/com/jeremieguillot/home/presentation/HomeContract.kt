package com.jeremieguillot.home.presentation

import com.jeremieguillot.core.presentation.model.CleaningAreaUi
import com.jeremieguillot.core.presentation.ui.UiText

/**
 * Contract for the Home screen .
 */
sealed class HomeContract {

    /**
     * Represents user-initiated actions on the Home screen.
     */
    sealed class Action {
        data object NavigateToDeclareCleaningArea : Action()
        data class NavigateToCleaningArea(val id: Long) : Action()
    }

    /**
     * Represents the possible states of the Home screen.
     */
    data class State(
        val currentCleaningAreas: List<CleaningAreaUi> = emptyList(),
        val isLoading: Boolean = true,
        val errorMessage: String? = null
    )

    /**
     * Represents one-time events emitted by the ViewModel to the UI.
     */
    sealed class Event {
        data class ShowError(val message: UiText) : Event()
    }
}