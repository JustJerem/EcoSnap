package com.jeremieguillot.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeremieguillot.core.domain.CleaningStatus
import com.jeremieguillot.designsystem.DraftBackground
import com.jeremieguillot.designsystem.InProgressBackground

@Composable
fun StatusBadge(status: CleaningStatus) {
    val (backgroundColor, textColor) = when (status) {
        CleaningStatus.SUBMITTED -> MaterialTheme.colorScheme.secondary to Color.White
        CleaningStatus.IN_PROGRESS -> InProgressBackground to Color.Black
        CleaningStatus.DRAFT -> DraftBackground to Color.White
    }
    Box(
        modifier = Modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = status.name,
            color = textColor,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StatusBadgeAllPreviews() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        StatusBadge(status = CleaningStatus.SUBMITTED)
        StatusBadge(status = CleaningStatus.IN_PROGRESS)
        StatusBadge(status = CleaningStatus.DRAFT)
    }
}