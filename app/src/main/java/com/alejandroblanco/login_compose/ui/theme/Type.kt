package com.alejandroblanco.login_compose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alejandroblanco.login_compose.R

// Fuentes Monserrat y Poppins:
// Montserrat para los titulos
val Montserrat = FontFamily(
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold),
)

// Poppins para el resto de textos
val Poppins = FontFamily(
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
)



private val base = Typography()

val Typography = Typography(
    // titulos
    headlineMedium = base.headlineMedium.copy(
        fontFamily = Montserrat, fontWeight = FontWeight.Bold,
    ),
    titleLarge = base.titleLarge.copy(
        fontFamily = Montserrat, fontWeight = FontWeight.SemiBold,
    ),
    // textos normales y botones
    bodyLarge = base.bodyLarge.copy(fontFamily = Poppins),
    bodyMedium = base.bodyMedium.copy(fontFamily = Poppins),
    bodySmall = base.bodySmall.copy(fontFamily = Poppins),
    labelLarge = base.labelLarge.copy(
        fontFamily = Poppins, fontWeight = FontWeight.Medium,
    ),
)
