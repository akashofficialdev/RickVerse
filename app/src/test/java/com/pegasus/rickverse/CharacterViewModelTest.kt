package com.pegasus.rickverse

import com.pegasus.rickverse.presentation.list.state.CharacterListState
import com.pegasus.rickverse.presentation.list.viewmodel.CharacterViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelTest {

    private lateinit var viewModel: CharacterViewModel
    private lateinit var repository: FakeCharacterRepository

    @Before
    fun setup() {
        repository = FakeCharacterRepository()
        viewModel = CharacterViewModel(repository)
    }

    @Test
    fun `getCharacters updates state with character list`() = runTest {
        // Collect the flow manually to get updates
        val collectedStates = mutableListOf<CharacterListState>()
        val job = launch {
            viewModel.uiState.collect {
                collectedStates.add(it)
                if (!it.isLoading) cancel()
            }
        }

        advanceUntilIdle()
        job.join()

        val finalState = collectedStates.last()

        assertFalse("Loading should be false", finalState.isLoading)
        assertTrue("Characters list should not be empty", finalState.characters.isNotEmpty())
        assertNull("Error should be null", finalState.error)
    }
}

