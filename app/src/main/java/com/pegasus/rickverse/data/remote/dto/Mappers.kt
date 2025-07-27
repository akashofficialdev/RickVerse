package com.pegasus.rickverse.data.remote.dto

import com.pegasus.rickverse.domain.model.Character

fun List<CharacterDto>.toCharacterList(): List<Character> {
    return map {
        Character(
            id = it.id,
            name = it.name,
            status = it.status,
            species = it.species,
            gender = it.gender,
            imageUrl = it.image,
            origin = it.origin.name,
            location = it.location.name
        )
    }
}
