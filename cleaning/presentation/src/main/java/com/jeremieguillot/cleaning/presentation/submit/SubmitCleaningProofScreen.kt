@file:OptIn(ExperimentalMaterial3Api::class)

package com.jeremieguillot.cleaning.presentation.submit

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jeremieguillot.EcosnapButton
import com.jeremieguillot.cleaning.presentation.R
import com.jeremieguillot.cleaning.presentation.composables.BeforeAfterImagePlaceholder
import com.jeremieguillot.cleaning.presentation.composables.GamificationCounter
import com.jeremieguillot.cleaning.presentation.composables.MissionReviewDialog
import com.jeremieguillot.cleaning.presentation.composables.TaggedParticipantsInput
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SubmitCleaningProofScreenRoot(
    viewModel: SubmitCleaningProofViewModel,
    navigateToTakePhoto: () -> Unit,
    onBack: () -> Boolean
) {
    val context = LocalContext.current
    var displaySubmittedProof by remember { mutableStateOf(false) }
    LaunchedEffect(viewModel.events) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is SubmitCleaningProofContract.Event.ProofSubmitted -> {
                    displaySubmittedProof = true
                }

                is SubmitCleaningProofContract.Event.ShowError -> {
                    Toast.makeText(context, event.message.asString(context), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    if (displaySubmittedProof) {
        MissionReviewDialog {
            displaySubmittedProof = false
            onBack()
        }
    }


    val state by viewModel.state.collectAsState()
    SubmitCleaningProofScreen(
        state = state,
        onAction = { action ->
            when (action) {
                SubmitCleaningProofContract.Action.NavigateBack -> onBack()
                SubmitCleaningProofContract.Action.NavigateToTakePhoto -> navigateToTakePhoto()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun SubmitCleaningProofScreen(
    state: SubmitCleaningProofContract.State,
    onAction: (SubmitCleaningProofContract.Action) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.submit_cleaning_proof)) },
                navigationIcon = {
                    IconButton(onClick = { onAction(SubmitCleaningProofContract.Action.NavigateBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cd_back)
                        )
                    }
                },
                actions = { GamificationCounter(state.points) }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                when {
                    state.isLoading -> CircularProgressIndicator(Modifier.fillMaxWidth())
                    else -> {
                        state.cleaningArea?.photoPaths?.first()?.let { photoPath ->
                            BeforeAfterImagePlaceholder(photoPath, state.afterPhoto) {
                                onAction(SubmitCleaningProofContract.Action.NavigateToTakePhoto)
                            }
                        }
                    }
                }
                TaggedParticipantsInput(
                    taggedParticipants = state.taggedParticipants,
                    addParticipant = {
                        onAction(SubmitCleaningProofContract.Action.AddParticipant(it))
                    },
                    removeParticipant = {
                        onAction(
                            SubmitCleaningProofContract.Action.RemoveParticipant(
                                it
                            )
                        )
                    },
                )
                // Submit Button
                EcosnapButton(
                    text = stringResource(R.string.submit),
                    onClick = { onAction(SubmitCleaningProofContract.Action.SubmitProof) },
                    isEnabled = state.afterPhoto != null
                )
            }
        }
    )
}