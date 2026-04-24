package com.example.gerenciamentodeviagens.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun GerenciamentoViagensTema(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = lightColorScheme(
        primary = Color(0xFF0D47A1), onPrimary = Color.White,
        secondary = Color(0xFF1976D2), onSecondary = Color.White,
        background = Color(0xFFF5F5F5), surface = Color.White,
        error = Color(0xFFB00020)
    ), content = content)
}
