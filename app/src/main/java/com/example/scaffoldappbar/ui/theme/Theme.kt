package com.example.scaffoldappbar.ui.theme  // Ensure this matches your package structure

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun ScaffoldTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(),  // Default color scheme
        typography = Typography,
        content = content
    )
}
