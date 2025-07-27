package com.pegasus.rickverse

import com.pegasus.rickverse.domain.model.Character
import com.pegasus.rickverse.domain.repository.CharacterRepository
import com.pegasus.rickverse.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCharacterRepository : CharacterRepository {

    override fun getCharacters(page: Int): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading)
        emit(Resource.Success(
            listOf(
                Character(
                    id = 1,
                    name = "Rick Sanchez",
                    status = "Alive",
                    species = "Human",
                    gender = "Male",
                    origin = "Earth (C-137)",
                    location = "Citadel of Ricks",
                    imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
                ),
                Character(
                    id = 2,
                    name = "Morty Smith",
                    status = "Alive",
                    species = "Human",
                    gender = "Male",
                    origin = "Earth (C-137)",
                    location = "Earth (Replacement Dimension)",
                    imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
                )
            )
        ))
    }
}

