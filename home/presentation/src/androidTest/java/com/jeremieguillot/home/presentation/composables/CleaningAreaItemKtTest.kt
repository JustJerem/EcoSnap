package com.jeremieguillot.home.presentation.composables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.jeremieguillot.core.domain.CleaningArea
import com.jeremieguillot.core.domain.CleaningStatus
import com.jeremieguillot.core.domain.toLocalizedDateString
import org.junit.Rule
import org.junit.Test
import java.time.ZonedDateTime

@get:Rule
val composeTestRule = createComposeRule()

class CleaningAreaItemTest {

    @Test
    fun cleaningAreaItem_showsCorrectPhotoCount() {
        // Given a CleaningArea with 3 photos
        val testCleaningArea = CleaningArea(
            id = 1,
            dateTimeUtc = ZonedDateTime.now(),
            photoPaths = listOf("photo1.jpg", "photo2.jpg", "photo3.jpg"),
            status = CleaningStatus.IN_PROGRESS
        )

        // Launch the composable
        composeTestRule.setContent {
            CleaningAreaItem(area = testCleaningArea, onTap = {})
        }

        // Verify
        composeTestRule
            .onNodeWithText("3 photos")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("IN_PROGRESS")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(ZonedDateTime.now().toLocalizedDateString())
            .assertIsDisplayed()
    }
}