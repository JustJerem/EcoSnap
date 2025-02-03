package com.jeremieguillot.camera.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CameraOverlay() {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        // Full black overlay
        drawRect(
            color = Color.Black.copy(alpha = 0.7f),
            size = size
        )

        // Transparent rounded rectangle in the center
        val rectWidth = size.width * 0.8f
        val rectHeight = size.height * 0.5f
        val left = (size.width - rectWidth) / 2
        val top = (size.height - rectHeight) / 2

        drawRoundRect(
            color = Color.Transparent,
            topLeft = Offset(left, top),
            size = Size(rectWidth, rectHeight),
            cornerRadius = CornerRadius(32.dp.toPx(), 32.dp.toPx()),
            blendMode = BlendMode.Clear // Transparent
        )
    }
}