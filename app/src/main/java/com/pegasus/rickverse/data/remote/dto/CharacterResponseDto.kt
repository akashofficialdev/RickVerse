package com.pegasus.rickverse.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterResponseDto(
    @SerializedName("info")
    val info: InfoDto,

    @SerializedName("results")
    val results: List<CharacterDto>
)

data class InfoDto(
    @SerializedName("count")
    val count: Int,

    @SerializedName("pages")
    val pages: Int,

    @SerializedName("next")
    val next: String?,

    @SerializedName("prev")
    val prev: String?
)
