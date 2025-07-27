package com.pegasus.rickverse.domain.repository

import com.pegasus.rickverse.domain.model.Character
import com.pegasus.rickverse.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(page: Int = 1): Flow<Resource<List<Character>>>
}
