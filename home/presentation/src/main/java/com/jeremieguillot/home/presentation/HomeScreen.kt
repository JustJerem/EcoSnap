package com.jeremieguillot.home.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jeremieguillot.EcosnapButton
import com.jeremieguillot.home.presentation.composables.CleaningAreaItem
import com.jeremieguillot.home.presentation.composables.EmptyStateScreen
import com.jeremieguillot.home.presentation.composables.UpcomingFeatureCard
import kotlinx.coroutines.flow.collectLatest


@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel,
    navigateToCleaningArea: () -> Unit,
    navigateToCleaningAreaSubmit: (Long) -> Unit,
) {

    val context = LocalContext.current
    LaunchedEffect(viewModel.events) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is HomeContract.Event.ShowError -> {
                    Toast.makeText(context, event.message.asString(context), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
    val state by viewModel.state.collectAsState()
    HomeScreen(
        state = state,
        onAction = { action ->
            when (action) {
                HomeContract.Action.NavigateToDeclareCleaningArea -> navigateToCleaningArea()
                is HomeContract.Action.NavigateToCleaningArea -> navigateToCleaningAreaSubmit(action.id)
                else -> Unit
            }
        },
    )
}

@Composable
fun HomeScreen(
    state: HomeContract.State,
    onAction: (HomeContract.Action) -> Unit,
) {
    Scaffold { paddings ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddings)
        ) {
            when {
                state.isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
                state.currentCleaningAreas.isEmpty() -> EmptyStateScreen()
                else -> LazyColumn(
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(state.currentCleaningAreas) {
                        CleaningAreaItem(it) {
                            onAction(
                                HomeContract.Action.NavigateToCleaningArea(it.id)
                            )
                        }
                    }
                    item { UpcomingFeatureCard() }
                }
            }
            EcosnapButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                text = stringResource(R.string.declare_a_cleaning_area),
                onClick = {
                    onAction(HomeContract.Action.NavigateToDeclareCleaningArea)
                },
            )
        }
    }
}