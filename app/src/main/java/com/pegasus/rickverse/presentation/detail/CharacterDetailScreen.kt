package com.pegasus.rickverse.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pegasus.rickverse.domain.model.Character

@Composable
fun CharacterDetailScreen(character: Character) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6B45A7),
                        Color(0xFFE58A94),
                        Color(0xFFFFC9A1)
                    )
                )
            )
    ) {
        Scaffold(
            containerColor = Color.Transparent, // Important
            contentColor = MaterialTheme.colorScheme.onBackground
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
            ) {
                AsyncImage(
                    model = character.imageUrl,
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = character.name, style = MaterialTheme.typography.headlineLarge,
                    color = Color.White
                )
                DetailText(text = "Status: ${character.status}")
                DetailText(text = "Species: ${character.species}")
                DetailText(text = "Gender: ${character.gender}")
                DetailText(text = "Origin: ${character.origin}")
                DetailText(text = "Location: ${character.location}")
            }
        }
    }
}

@Composable
fun DetailText(text: String) {
    Text(
        text = text,
        color = Color.White
    )
}
