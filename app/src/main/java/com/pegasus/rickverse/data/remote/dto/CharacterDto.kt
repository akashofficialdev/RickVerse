package com.pegasus.rickverse.data.remote.dto

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val origin: Details,
    val location: Details
)

data class Details(
    val name: String,
    val url: String
)
