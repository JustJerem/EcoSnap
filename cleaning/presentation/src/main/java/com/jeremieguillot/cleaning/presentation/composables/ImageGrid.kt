package com.jeremieguillot.cleaning.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeremieguillot.cleaning.presentation.declare.DeclareCleaningContract
import com.jeremieguillot.core.domain.Constants

@Composable
fun ImageGrid(
    modifier: Modifier = Modifier,
    images: List<String>, onAction: (DeclareCleaningContract.Action) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        userScrollEnabled = false
    ) {
        items(images.take(Constants.MAX_IMAGE_COUNT)) { imagePath ->
            DeclaredImage(
                imagePath = imagePath,
                onRemove = { onAction(DeclareCleaningContract.Action.RemoveImage(imagePath)) }
            )
        }
    }
}