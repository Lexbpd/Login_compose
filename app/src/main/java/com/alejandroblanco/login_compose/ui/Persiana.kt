package com.alejandroblanco.login_compose.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Persiana de madera que se cierra y se abre para tapar el cambio de modo.
// Son varias lamas horizontales que van creciendo hasta tapar toda la pantalla.
private const val NUM_LAMAS = 12
private val MaderaClara = Color(0xFF7A5A3A)
private val MaderaOscura = Color(0xFF4E3624)
private val Cinta = Color(0xFF2F2420)

// progreso va de 0f a 2f:
//   de 0 a 1 la persiana se CIERRA (las lamas van tapando de arriba a abajo)
//   de 1 a 2 la persiana se ABRE (las lamas van abriendo tambien de arriba a abajo)
// Lo pido como funcion para que solo se redibuje el Canvas y no toda la pantalla
@Composable
fun Persiana(progreso: () -> Float, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val v = progreso()
        if (v <= 0f || v >= 2f) return@Canvas

        val altoLama = size.height / NUM_LAMAS
        val retraso = 0.7f   // cuanto se retrasa cada lama respecto a la de arriba (mas = cascada mas larga)

        for (i in 0 until NUM_LAMAS) {
            // cada lama empieza un poco despues que la anterior (efecto cascada)
            val inicio = i.toFloat() / NUM_LAMAS * retraso
            val cierre = ((v - inicio) / (1f - retraso)).coerceIn(0f, 1f)
            val apertura = ((v - 1f - inicio) / (1f - retraso)).coerceIn(0f, 1f)
            val giro = cierre - apertura
            if (giro <= 0f) continue

            // la lama gira: de verse de canto (muy fina) a verse de frente (entera)
            // el 1.04 es para que al cerrar no queden huecos entre lamas
            val alto = altoLama * 1.04f * giro
            val arriba = (i + 0.5f) * altoLama - alto / 2

            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(MaderaClara, MaderaOscura),
                    startY = arriba,
                    endY = arriba + alto,
                ),
                topLeft = Offset(0f, arriba),
                size = Size(size.width, alto),
            )
            // sombra fina debajo de cada lama
            drawLine(
                color = Color.Black.copy(alpha = 0.5f),
                start = Offset(0f, arriba + alto),
                end = Offset(size.width, arriba + alto),
                strokeWidth = 2f,
            )
        }

        // las dos cintas oscuras que sujetan las lamas, como en una persiana de verdad
        // aparecen al empezar a cerrar y desaparecen al terminar de abrir
        val alfaCinta = if (v <= 1f) (v * 4f).coerceIn(0f, 1f) else (2f - v).coerceIn(0f, 1f)
        listOf(0.18f, 0.82f).forEach { x ->
            drawRect(
                color = Cinta.copy(alpha = alfaCinta),
                topLeft = Offset(size.width * x - 10f, 0f),
                size = Size(20f, size.height),
            )
        }
    }
}
