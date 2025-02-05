package com.jeremieguillot.cleaning.presentation.composables

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jeremieguillot.cleaning.presentation.R
import com.jeremieguillot.core.presentation.ui.loadBitmapFromPath


@Composable
fun BeforeAfterImagePlaceholder(
    beforeImage: Bitmap,
    afterImage: String?,
    onPlaceholderClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Before Column
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BeforeAfterTitle(stringResource(R.string.before))
            DecoratedImage(beforeImage)
        }

        // After Column
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BeforeAfterTitle(stringResource(R.string.after))
            when {
                afterImage != null -> {
                    val bitmap by remember(afterImage) {
                        mutableStateOf(loadBitmapFromPath(afterImage))
                    }
                    DecoratedImage(bitmap)
                }
                else -> AfterPlaceholder(
                    onPlaceholderClick = onPlaceholderClick
                )
            }
        }
    }
}

@Composable
private fun BeforeAfterTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

