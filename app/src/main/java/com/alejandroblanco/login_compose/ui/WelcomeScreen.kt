package com.alejandroblanco.login_compose.ui
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandroblanco.login_compose.ui.theme.Login_composeTheme
import com.alejandroblanco.login_compose.ui.theme.Primary
import com.alejandroblanco.login_compose.ui.theme.Secondary
import com.alejandroblanco.login_compose.ui.theme.Tertiary

/*
 * PANTALLA 2: BIENVENIDA
 *
 * Recibe DOS cosas desde fuera:
 *   - "nombre": el nombre del usuario, que solo mostramos (parámetro de DATOS).
 *   - "onCerrarSesion": una función que llamamos al pulsar el botón (parámetro de ACCIÓN).
 * La pantalla no sabe qué pasa después de cerrar sesión: eso lo decide quien la usa.
 */
@Composable
fun WelcomeScreen(
    nombre: String,
    onCerrarSesion: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Column con "horizontalAlignment" centra todos sus hijos en horizontal.
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(32.dp))

        // el mismo logo del pato que en el login
        LogoCircular()

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Bienvenido a la cocina ",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = nombre,   // <- el parámetro que nos envió la pantalla de login
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(Modifier.height(24.dp))

        // weight(1f) para que ocupe el hueco y el boton quede abajo
        IlustracionPlato(modifier = Modifier.weight(1f))

        Spacer(Modifier.height(24.dp))

        // Botón con contorno, centrado gracias al "horizontalAlignment" de la Column.
        OutlinedButton(
            onClick = onCerrarSesion,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
        ) {
            Text("Cerrar sesión")
        }

        Spacer(Modifier.height(16.dp))
    }
}

// Mi ilustracion
@Composable
private fun IlustracionPlato(modifier: Modifier = Modifier) {
    // uso los colores fuertes en los dos modos, con los del modo oscuro se veia muy apagado
    val campana = Primary
    val bandeja = Secondary
    val dorado = Tertiary
    val brillo = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.35f)   // blanco casi transparente

    Canvas(modifier = modifier.fillMaxWidth()) {
        // "u" escala el dibujo (312x300) al espacio que haya
        val u = minOf(size.width / 312f, size.height / 300f)

        // translate lo centra en la pantalla
        translate(
            left = (size.width - 312f * u) / 2f,
            top = (size.height - 300f * u) / 2f,
        ) {
            // bandeja
            drawOval(bandeja, topLeft = Offset(20 * u, 252 * u), size = Size(272 * u, 36 * u))

            // cupula
            drawArc(
                color = campana,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(52 * u, 120 * u),
                size = Size(208 * u, 300 * u),
            )

            // base de la campana
            drawRoundRect(
                color = campana,
                topLeft = Offset(44 * u, 266 * u),
                size = Size(224 * u, 14 * u),
                cornerRadius = CornerRadius(7 * u),
            )

            // tirador dorado de arriba
            drawRoundRect(
                color = dorado,
                topLeft = Offset(148 * u, 116 * u),
                size = Size(16 * u, 12 * u),
                cornerRadius = CornerRadius(4 * u),
            )
            drawCircle(dorado, radius = 14 * u, center = Offset(156 * u, 110 * u))

            // brillo de la cupula
            drawArc(
                color = brillo,
                startAngle = 200f,
                sweepAngle = 45f,
                useCenter = false,
                topLeft = Offset(76 * u, 150 * u),
                size = Size(160 * u, 240 * u),
                style = Stroke(width = 8 * u, cap = StrokeCap.Round),
            )

            // destellos alrededor
            drawSparkle(Offset(36 * u, 90 * u), 12 * u, dorado)
            drawSparkle(Offset(276 * u, 60 * u), 14 * u, campana)
            drawSparkle(Offset(280 * u, 180 * u), 9 * u, dorado)
            drawSparkle(Offset(30 * u, 190 * u), 9 * u, campana)
        }
    }
}

//Destello
private fun DrawScope.drawSparkle(centro: Offset, medida: Float, color: Color) {
    val c = medida * 0.2f   // distancia de las "esquinas interiores" al centro
    val estrella = Path().apply {
        moveTo(centro.x, centro.y - medida)       // punta de arriba
        lineTo(centro.x + c, centro.y - c)
        lineTo(centro.x + medida, centro.y)       // punta de la derecha
        lineTo(centro.x + c, centro.y + c)
        lineTo(centro.x, centro.y + medida)       // punta de abajo
        lineTo(centro.x - c, centro.y + c)
        lineTo(centro.x - medida, centro.y)       // punta de la izquierda
        lineTo(centro.x - c, centro.y - c)
        close()
    }
    drawPath(estrella, color)
}

// Vista previa con el tamaño del Samsung Galaxy S24. Aquí el nombre es solo de ejemplo.
@Preview(name = "Galaxy S24", showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun WelcomeScreenPreview() {
    Login_composeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            WelcomeScreen(nombre = "Juan Carlos", onCerrarSesion = {})
        }
    }
}
