package com.alejandroblanco.login_compose.ui



import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandroblanco.login_compose.R
import com.alejandroblanco.login_compose.ui.theme.Login_composeTheme

// ---------- DATOS DE PRUEBA (hardcodeados) ----------
// En una app real estos datos vendrían de un servidor o de una base de datos.
// Para el taller los dejamos escritos aquí, a la vista de todos.
private const val CORREO_CORRECTO = "pruebaejercicio@gmail.com"
private const val CLAVE_CORRECTA = "1234"
private const val NOMBRE_USUARIO = "Alex Blanco"

/*
 * PANTALLA 1: LOGIN
 *
 * Idea clave de Compose: la pantalla es una FUNCIÓN que dibuja la interfaz
 * a partir de un ESTADO. Cuando el estado cambia (por ejemplo, al escribir),
 * Compose vuelve a ejecutar la función y la pantalla se actualiza sola.
 *
 * Esta pantalla NO sabe cómo se abre la siguiente. Solo recibe una función
 * ("onLoginCorrecto") y la llama cuando el usuario acierta, entregándole el
 * nombre. Quien la use (MainActivity) decide qué hacer con él.
 */
@Composable
fun LoginScreen(
    onLoginCorrecto: (nombre: String) -> Unit,
    modifier: Modifier = Modifier,
) {

    // ---------- ESTADO ----------
    // "rememberSaveable" guarda el valor mientras la pantalla está viva (y aunque
    // se gire el móvil); "mutableStateOf" avisa a Compose cuando cambia para que redibuje.
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    // el mensaje de error: si es null no hay error, si tiene texto se muestra
    var error by rememberSaveable { mutableStateOf<String?>(null) }

    // ---------- DISEÑO ----------
    // Column = apila los elementos en vertical (como un Auto Layout vertical en Figma).
    Column(
        modifier = modifier
            .fillMaxSize()                 // ocupa toda la pantalla
            .padding(horizontal = 24.dp)   // margen lateral de 24 como en Figma
            .imePadding(),                 // sube el contenido cuando aparece el teclado
    ) {
        Spacer(Modifier.height(40.dp))     // Spacer = espacio vacío

        // --- Cabecera: logo + título + subtítulo ---
        LogoCircular()
        Spacer(Modifier.height(24.dp))
        Text(
            text = "¡Cuac! Hola de nuevo",
            style = MaterialTheme.typography.headlineMedium,   // estilo del tema, no tamaños sueltos
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Tu mesa te está esperando",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(Modifier.height(32.dp))

        // --- Campo de correo ---
        // OutlinedTextField es el campo de texto con borde de Material 3: ya trae
        // la etiqueta flotante y el borde morado al enfocar. "isError" lo pone rojo.
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it           // guardamos lo escrito en el estado
                error = null         // y quitamos el error mientras el usuario corrige
            },
            label = { Text("Tu correo") },
            singleLine = true,
            isError = error != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(16.dp))

        // --- Campo de contraseña ---
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                error = null
            },
            label = { Text("Contraseña") },
            singleLine = true,
            isError = error != null,
            // "supportingText" es el texto pequeño bajo el campo: solo lo mostramos si hay error.
            supportingText = { error?.let { Text(it) } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            // Si la contraseña es visible mostramos el texto tal cual; si no, puntos.
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            // Icono del ojo a la derecha: al pulsarlo cambia el estado.
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(
                            if (passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility
                        ),
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(24.dp))

        // --- Botón principal: AQUÍ ESTÁ LA LÓGICA DEL LOGIN ---
        Button(
            onClick = {
                val correo = email.trim()
                // el when mira los casos en orden y se queda con el primero que falla
                error = when {
                    // el correo tiene que parecer un correo (con @ y punto)
                    !Patterns.EMAIL_ADDRESS.matcher(correo).matches() -> "Ese correo no tiene pinta de correo"
                    // contraseña muy corta
                    password.length < 4 -> "La contraseña es demasiado corta"
                    // formato bien pero los datos no son los correctos
                    correo != CORREO_CORRECTO || password != CLAVE_CORRECTA ->
                        "Ese usuario no está en la carta: revisa tus datos"
                    // todo bien, no hay error
                    else -> null
                }
                // si no hay error entramos y mandamos el nombre
                if (error == null) onLoginCorrecto(NOMBRE_USUARIO)
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Entrar a la cocina")
        }
    }
}

// Vista previa en Android Studio, con el tamaño exacto del Samsung Galaxy S24 (360 x 780 dp).
@Preview(name = "Galaxy S24", showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun LoginScreenPreview() {
    Login_composeTheme {
        // En la app real el color de fondo lo pone el Scaffold; aquí usamos Surface.
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreen(onLoginCorrecto = {})
        }
    }
}