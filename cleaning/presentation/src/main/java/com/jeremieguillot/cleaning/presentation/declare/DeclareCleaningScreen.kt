package com.jeremieguillot.cleaning.presentation.declare

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeremieguillot.cleaning.presentation.R
import com.jeremieguillot.cleaning.presentation.composables.ActionButtons
import com.jeremieguillot.cleaning.presentation.composables.CleaningTopBar
import com.jeremieguillot.cleaning.presentation.composables.ImageGrid
import com.jeremieguillot.cleaning.presentation.composables.MissionDetailsSection
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


/**
 * Displays the cleaning river screen, showing the current mission, points,
 * and options to take a photo or continue to the next mission.
 */
@Composable
fun CleaningScreenRoot(
    viewModel: DeclareCleaningViewModel = koinViewModel(),
    navigateToTakePhoto: () -> Unit,
    navigateBack: () -> Unit
) {

    val context = LocalContext.current
    LaunchedEffect(viewModel.events) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is DeclareCleaningContract.Event.ShowToastAndBackHome -> {
                    Toast.makeText(
                        /* context = */
                        context,
                        /* text = */
                        context.getString(R.string.cleaning_area_saved_mother_earth_thanks_you),
                        /* duration = */
                        Toast.LENGTH_LONG,
                    ).show()
                    navigateBack()
                }

                is DeclareCleaningContract.Event.ShowError -> {
                    Toast.makeText(context, event.message.asString(context), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    val state by viewModel.state.collectAsState()
    CleaningScreen(
        state = state,
        onAction = { action ->
            when (action) {
                DeclareCleaningContract.Action.NavigateToTakePhoto -> navigateToTakePhoto()
                DeclareCleaningContract.Action.Back -> navigateBack()
                else -> Unit
            }
            viewModel.onAction(action)
        },
    )
}

@Composable
private fun CleaningScreen(
    state: DeclareCleaningContract.State, onAction: (DeclareCleaningContract.Action) -> Unit
) {
    Scaffold(
        topBar = {
            CleaningTopBar(state.points, onAction)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
        ) {
            when {
                state.imagesPath.isEmpty() -> MissionDetailsSection(
                    modifier = Modifier.weight(1f),
                    subtitle = "Step ${state.currentMission} of ${state.totalMissions}"
                )

                else -> ImageGrid(
                    modifier = Modifier.weight(1f),
                    images = state.imagesPath,
                    onAction = onAction
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                ActionButtons(
                    onTakePhotoClick = { onAction(DeclareCleaningContract.Action.NavigateToTakePhoto) },
                    startCleaningClick = { onAction(DeclareCleaningContract.Action.ValidateCleaningArea) },
                    isNextEnabled = state.isNextButtonEnabled
                )
            }
        }
    }
}

@Preview
@Composable
private fun CleaningScreen1Prev() {
    CleaningScreen(DeclareCleaningContract.State(imagesPath = listOf(""))) {}
}

@Preview
@Composable
private fun CleaningScreen2Prev() {
    CleaningScreen(DeclareCleaningContract.State()) {}
}