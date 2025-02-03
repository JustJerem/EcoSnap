package com.jeremieguillot.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeremieguillot.core.domain.CleaningRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val cleaningRepository: CleaningRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeContract.State())
    val state = _state.asStateFlow()


    private val eventChannel = Channel<HomeContract.Event>()
    val events = eventChannel.receiveAsFlow()

    init {
        loadOngoingCleaningArea()
    }

    private fun loadOngoingCleaningArea() {
        viewModelScope.launch {
            cleaningRepository.getCleaningArea()
                .collect { cleaningAreas ->
                    _state.update {
                        it.copy(
                            currentCleaningAreas = cleaningAreas,
                            isLoading = false
                        )
                    }
                }
        }
    }
}
