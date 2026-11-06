package com.alejandroblanco.login_compose.ui.theme

import androidx.compose.ui.graphics.Color

// Colores de Material Design 3 (esquema claro).
// Son exactamente los mismos "tokens" (variables) que creamos en Figma
// en la colección "M3 Colores": md/primary, md/surface, etc.
// Los colores salen de la plantilla pato (rojo y dorado, tipo restaurante)

val Primary = Color(0xFFD41B22)              // rojo de los botones
val OnPrimary = Color(0xFFFFFFFF)            // texto blanco sobre el rojo
val PrimaryContainer = Color(0xFFFFDAD6)     // rojo clarito, fondo del logo
val OnPrimaryContainer = Color(0xFF410002)
val Surface = Color(0xFFFAF7F4)              // fondo crema
val OnSurface = Color(0xFF1A1513)            // texto principal
val OnSurfaceVariant = Color(0xFF5F5853)     // texto secundario
val Outline = Color(0xFF8A817B)              // borde de los campos
val OutlineVariant = Color(0xFFE2D9D1)
val Secondary = Color(0xFFA8141A)            // rojo oscuro
val Tertiary = Color(0xFFB8863B)             // dorado


// Modo oscuro: mismos roles pero con colores mas claros sobre fondo oscuro
val DarkPrimary = Color(0xFFFFB4AB)
val DarkOnPrimary = Color(0xFF690005)
val DarkPrimaryContainer = Color(0xFFA8141A)
val DarkOnPrimaryContainer = Color(0xFFFFDAD6)
val DarkSurface = Color(0xFF15100E)          // fondo oscuro de pato
val DarkOnSurface = Color(0xFFEDE0DC)
val DarkOnSurfaceVariant = Color(0xFFD8C2BC)
val DarkOutline = Color(0xFFA08C87)
val DarkOutlineVariant = Color(0xFF534340)
val DarkSecondary = Color(0xFFE7BDB7)
val DarkTertiary = Color(0xFFE6C28B)
