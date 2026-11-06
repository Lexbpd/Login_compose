package com.alejandroblanco.login_compose.ui

import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

// Boton para cambiar entre modo claro y oscuro.
// No guarda el estado: recibe el modo actual y avisa con "onCambiar" cuando se pulsa.
@Composable
fun BotonModo(
    modoOscuro: Boolean,
    onCambiar: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onCambiar,
        // descripcion para los lectores de pantalla
        modifier = modifier.semantics {
            contentDescription = if (modoOscuro) "Cambiar a modo claro" else "Cambiar a modo oscuro"
        },
    ) {
        // si estamos en oscuro mostramos el sol, y si no la luna
        Text(
            text = if (modoOscuro) "☀️" else "🌙",
            style = MaterialTheme.typography.titleLarge,
        )
    }
}
