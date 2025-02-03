@file:OptIn(ExperimentalMaterial3Api::class)

package com.jeremieguillot.cleaning.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jeremieguillot.cleaning.presentation.R
import com.jeremieguillot.cleaning.presentation.declare.DeclareCleaningContract

@Composable
fun CleaningTopBar(points: Int, onAction: (DeclareCleaningContract.Action) -> Unit) {
    TopAppBar(
        title = { /*Nothing*/ },
        navigationIcon = {
            IconButton(onClick = { onAction(DeclareCleaningContract.Action.Back) }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.cd_back_to_home)
                )
            }
        },
        actions = { GamificationCounter(points) }
    )
}