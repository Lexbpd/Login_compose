package com.alejandroblanco.login_compose.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Esquema claro: le dice a Material que color usa en cada rol
private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    tertiary = Tertiary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    surface = Surface,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant,
    outline = Outline,
    outlineVariant = OutlineVariant,
    background = Surface,
    onBackground = OnSurface,
)

// Esquema oscuro: igual que el claro pero con los colores Dark
private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    secondary = DarkSecondary,
    tertiary = DarkTertiary,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkOnPrimaryContainer,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    onSurfaceVariant = DarkOnSurfaceVariant,
    outline = DarkOutline,
    outlineVariant = DarkOutlineVariant,
    background = DarkSurface,
    onBackground = DarkOnSurface,
)

@Composable
fun Login_composeTheme(
    // por defecto usa el modo del movil, pero en los preview se puede forzar
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        // elegimos el esquema segun si el movil esta en modo oscuro
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content,
    )
}
