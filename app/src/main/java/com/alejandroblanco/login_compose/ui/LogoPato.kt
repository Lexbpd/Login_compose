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

/**
 * Pato dibujado con Canvas. Las medidas están pensadas para un lienzo de 100 x 100
 * y se escalan con "u", así el dibujo se ve igual en cualquier tamaño.
 */
@Composable
fun LogoPato(modifier: Modifier = Modifier) {
    // Los colores se leen del tema ANTES del Canvas (dentro no se puede usar MaterialTheme).
    val cuerpo = MaterialTheme.colorScheme.primary
    val ala = MaterialTheme.colorScheme.secondary
    val pico = MaterialTheme.colorScheme.tertiary
    val ojo = MaterialTheme.colorScheme.onPrimary

    Canvas(modifier = modifier.size(44.dp)) {
        val u = size.minDimension / 100f   // unidad de escala

        // Cuerpo
        drawOval(cuerpo, topLeft = Offset(10 * u, 46 * u), size = Size(64 * u, 38 * u))

        // Cola: un triángulo a la izquierda
        val cola = Path().apply {
            moveTo(14 * u, 60 * u); lineTo(2 * u, 46 * u); lineTo(28 * u, 52 * u); close()
        }
        drawPath(cola, cuerpo)

        // Cabeza
        drawCircle(cuerpo, radius = 16 * u, center = Offset(66 * u, 32 * u))

        // Pico: otro triángulo
        val picoForma = Path().apply {
            moveTo(79 * u, 28 * u); lineTo(97 * u, 35 * u); lineTo(79 * u, 42 * u); close()
        }
        drawPath(picoForma, pico)

        // Ala y ojo
        drawOval(ala, topLeft = Offset(26 * u, 56 * u), size = Size(30 * u, 18 * u))
        drawCircle(ojo, radius = 3 * u, center = Offset(70 * u, 28 * u))
    }
}