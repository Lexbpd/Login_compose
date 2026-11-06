package com.alejandroblanco.login_compose.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp

// Logo de la app: un pato dibujado con Canvas.
// Lo dibujo en un cuadrado de 100x100 y lo escalo con "u" para cualquier tamaño.
@Composable
fun LogoPato(modifier: Modifier = Modifier) {

    val cuerpo = MaterialTheme.colorScheme.primary
    val ala = MaterialTheme.colorScheme.secondary
    val pico = MaterialTheme.colorScheme.tertiary
    val ojo = MaterialTheme.colorScheme.onPrimary

    Canvas(modifier = modifier.size(44.dp)) {
        val u = size.minDimension / 100f   // unidad de escala

        // cuerpo
        drawOval(cuerpo, topLeft = Offset(10 * u, 46 * u), size = Size(64 * u, 38 * u))

        // cola
        val cola = Path().apply {
            moveTo(14 * u, 60 * u); lineTo(2 * u, 46 * u); lineTo(28 * u, 52 * u); close()
        }
        drawPath(cola, cuerpo)

        // cabeza
        drawCircle(cuerpo, radius = 16 * u, center = Offset(66 * u, 32 * u))

        // pico
        val picoForma = Path().apply {
            moveTo(79 * u, 28 * u); lineTo(97 * u, 35 * u); lineTo(79 * u, 42 * u); close()
        }
        drawPath(picoForma, pico)

        // ala y ojo
        drawOval(ala, topLeft = Offset(26 * u, 56 * u), size = Size(30 * u, 18 * u))
        drawCircle(ojo, radius = 3 * u, center = Offset(70 * u, 28 * u))
    }
}
