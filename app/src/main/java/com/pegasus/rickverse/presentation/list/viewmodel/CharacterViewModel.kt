package com.pegasus.rickverse.presentation.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pegasus.rickverse.domain.repository.CharacterRepository
import com.pegasus.rickverse.presentation.list.state.CharacterListState
import com.pegasus.rickverse.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterListState())
    val uiState: StateFlow<CharacterListState> = _uiState

    init {
        getCharacters()
    }

    fun refreshCharacters() {
        _uiState.update { it.copy(isRefreshing = true) }
        getCharacters(page = 1)
    }

    fun getCharacters(page: Int = 1) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(1000)

            repository.getCharacters(page).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                characters = result.data,
                                isLoading = false,
                                isRefreshing = false,
                                error = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                error = result.message ?: "Unexpected error",
                                isLoading = false,
                                isRefreshing = false
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }
}
