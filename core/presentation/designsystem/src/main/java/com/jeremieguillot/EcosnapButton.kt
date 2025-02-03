package com.jeremieguillot

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EcosnapButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    backgroundColor: Color = Color(0xFF518331),
    contentColor: Color = Color.White,
    shape: RoundedCornerShape = RoundedCornerShape(50),
    elevation: ButtonElevation = ButtonDefaults.elevatedButtonElevation()
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(56.dp), // Consistent button height
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) backgroundColor else Color.Gray, // Gray for disabled
            contentColor = if (isEnabled) contentColor else Color(0xFF9E9E9E) // Text color for disabled
        ),
        elevation = elevation
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun CustomButtonPrev() {
    EcosnapButton(text = "Start Cleaning", onClick = {})
}


@Preview
@Composable
private fun CustomButtonDisabledPrev() {
    EcosnapButton(text = "Start Cleaning", isEnabled = false, onClick = {})
}