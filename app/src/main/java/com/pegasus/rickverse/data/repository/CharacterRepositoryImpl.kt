package com.pegasus.rickverse.data.repository

import com.pegasus.rickverse.data.remote.RickAndMortyApi
import com.pegasus.rickverse.data.remote.dto.toCharacterList
import com.pegasus.rickverse.domain.model.Character
import com.pegasus.rickverse.domain.repository.CharacterRepository
import com.pegasus.rickverse.util.Resource
import com.pegasus.rickverse.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi
) : CharacterRepository {

    override fun getCharacters(page: Int): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading)
        emit(safeApiCall {
            api.getCharacters(page).results.toCharacterList()
        })
    }
}
