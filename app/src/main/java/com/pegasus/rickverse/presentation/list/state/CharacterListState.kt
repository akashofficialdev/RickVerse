package com.pegasus.rickverse.presentation.list.state

import com.pegasus.rickverse.domain.model.Character

data class CharacterListState(
    val characters: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)