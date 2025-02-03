package com.jeremieguillot.designsystem

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val InProgressBackground = Color(0xFFFFC107)
val DraftBackground = Color(0xFF757575)

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF388E3C),
    onPrimary = Color.White,
    primaryContainer = Color.White,
    onPrimaryContainer = Color.Black,

    secondary = Color(0xFF4CAF50),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFC8E6C9),
    onSecondaryContainer = Color.Black,

    tertiary = Color(0xFF795548),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFD7CCC8),
    onTertiaryContainer = Color.Black,

    background = Color.White,
    onBackground = Color(0xFF212121),

    surface = Color.White,
    onSurface = Color(0xFF212121),
    surfaceVariant = Color(0xFFE8F5E9),
    onSurfaceVariant = Color(0xFF37474F),

    surfaceTint = Color(0xFF388E3C),
    inverseSurface = Color(0xFF212121),
    inverseOnSurface = Color(0xFFF1F8E9),

    error = Color(0xFFD32F2F),
    onError = Color.White,
    errorContainer = Color(0xFFF9D3D3),
    onErrorContainer = Color.Black,

    outline = Color(0xFFBDBDBD),
    outlineVariant = Color(0xFF90A4AE),

    scrim = Color(0x66000000),
    surfaceContainer = Color(0xFFF0F0F0),
    surfaceContainerHigh = Color.White,
    surfaceContainerHighest = Color.White,
    surfaceContainerLow = Color.White,
    surfaceContainerLowest = Color.White,
)


val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF81C784),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF33691E),
    onPrimaryContainer = Color.White,

    secondary = Color(0xFFA5D6A7),
    onSecondary = Color.Black,

    tertiary = Color(0xFF6D4C41),
    onTertiary = Color.White,

    background = Color(0xFF1B5E20),
    onBackground = Color(0xFFE8F5E9),

    surface = Color(0xFF2E7D32),
    onSurface = Color.White,

    error = Color(0xFFEF9A9A),
    onError = Color.Black
)