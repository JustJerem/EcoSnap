package com.jeremieguillot.cleaning.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeremieguillot.EcosnapButton
import com.jeremieguillot.cleaning.presentation.R

/**
 * Displays action buttons for taking a photo and moving to the next mission.
 */
@Composable
fun ActionButtons(
    modifier: Modifier = Modifier,
    onTakePhotoClick: () -> Unit,
    startCleaningClick: () -> Unit,
    isNextEnabled: Boolean
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        EcosnapButton(
            text = stringResource(R.string.take_a_photo),
            onClick = onTakePhotoClick,
        )

        EcosnapButton(
            text = stringResource(R.string.start_cleaning),
            onClick = startCleaningClick,
            isEnabled = isNextEnabled,
        )
    }
}

@Preview
@Composable
private fun ActionButtonsEnabledPrev() {
    ActionButtons(onTakePhotoClick = {}, startCleaningClick = {}, isNextEnabled = true)
}

@Preview
@Composable
private fun ActionButtonsDisabledPrev() {
    ActionButtons(onTakePhotoClick = {}, startCleaningClick = {}, isNextEnabled = false)
}