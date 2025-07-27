package com.pegasus.rickverse.navigation

sealed class Screen(val route: String) {
    data object CharacterList : Screen("character_list")
    data object CharacterDetail : Screen("character_detail/{characterId}") {
        fun passId(id: Int) = "character_detail/$id"
    }
}
