package com.alejandroblanco.login_compose.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Logo: un círculo de color suave con el pato en el centro.
 *
 * Lo usan las DOS pantallas (login y bienvenida), asi no repito codigo.
 */
@Composable
fun LogoCircular() {
    // Box para poner el pato centrado encima del circulo
    Box(
        modifier = Modifier
            .size(72.dp)
            .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        LogoPato()
    }
}
