package com.pegasus.rickverse.presentation.list.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.pegasus.rickverse.presentation.list.viewmodel.CharacterViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.pegasus.rickverse.presentation.component.LoadingAnimation

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterListScreen(
    viewModel: CharacterViewModel = hiltViewModel(),
    onCharacterClick: (Int) -> Unit
) {
    val state = viewModel.uiState.collectAsState().value
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = { viewModel.refreshCharacters() }
    )
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
            when {
                state.isLoading -> {
                    LoadingAnimation(modifier = Modifier.align(Alignment.Center))
                }

                state.error != null -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .clickable { viewModel.refreshCharacters() }
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Something went wrong",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyLarge

                        )
                        Text(
                            text = "Tap to Refresh",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.characters) { character ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onCharacterClick(character.id) },
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            brush = Brush.linearGradient(
                                                colors = listOf(
                                                    Color(0xFF6B45A7),
                                                    Color(0xFFE58A94),
                                                    Color(0xFFFFC9A1)
                                                )

                                            )
                                        )
                                ) {
                                    Row(modifier = Modifier.padding(12.dp)) {
                                        Image(
                                            painter = rememberAsyncImagePainter(character.imageUrl),
                                            contentDescription = character.name,
                                            modifier = Modifier
                                                .size(120.dp)
                                                .padding(end = 12.dp)
                                                .clip(RoundedCornerShape(16.dp))
                                        )
                                        Column(
                                            modifier = Modifier.wrapContentSize(),
                                            verticalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            Text(
                                                text = character.name,
                                                style = MaterialTheme.typography.titleLarge,
                                                color = Color.White,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Text(
                                                text = "${character.species} • ${character.status}",
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = Color.White
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = state.isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}
