package com.pegasus.rickverse.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.pegasus.rickverse.presentation.detail.CharacterDetailScreen
import com.pegasus.rickverse.presentation.list.screen.CharacterListScreen
import com.pegasus.rickverse.presentation.list.viewmodel.CharacterViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.CharacterList.route,
        modifier = modifier,
        enterTransition = {
            slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(800)
            )
        },
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { -it },
                animationSpec = tween(800)
            )
        },
        popEnterTransition = {
            slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(800)
            )
        },
        popExitTransition = {
            slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(800)
            )
        }

    ) {
        composable(Screen.CharacterList.route) {
            CharacterListScreen(
                onCharacterClick = { characterId ->
                    navController.navigate(Screen.CharacterDetail.passId(characterId))

                }
            )
        }

        composable(
            route = Screen.CharacterDetail.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: return@composable
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.CharacterList.route)
            }
            val viewModel: CharacterViewModel = hiltViewModel(parentEntry)
            characterId.let { id ->
                val character = viewModel.uiState.value.characters.find { it.id == id }
                character?.let {
                    CharacterDetailScreen(character = it)
                }
            }
        }
    }
}
