package com.pegasus.rickverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pegasus.rickverse.navigation.AppNavHost
import com.pegasus.rickverse.presentation.ui.theme.RickVerseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickVerseTheme {
                AppNavHost()
            }
        }
    }
}
