package com.pegasus.rickverse

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import com.pegasus.rickverse.domain.model.Character
import com.pegasus.rickverse.presentation.detail.CharacterDetailScreen
import org.junit.Rule
import org.junit.Test

class CharacterDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun characterDetailScreen_displaysCharacterInfoCorrectly() {
        val fakeCharacter = Character(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            gender = "Male",
            origin = "Earth (C-137)",
            location = "Citadel of Ricks",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )

        composeTestRule.setContent {
            CharacterDetailScreen(character = fakeCharacter)
        }

        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()
        composeTestRule.onNodeWithText("Status: Alive").assertIsDisplayed()
        composeTestRule.onNodeWithText("Species: Human").assertIsDisplayed()
        composeTestRule.onNodeWithText("Gender: Male").assertIsDisplayed()
        composeTestRule.onNodeWithText("Origin: Earth (C-137)").assertIsDisplayed()
        composeTestRule.onNodeWithText("Location: Citadel of Ricks").assertIsDisplayed()
    }
}
