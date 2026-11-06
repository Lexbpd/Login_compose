package com.alejandroblanco.login_compose
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alejandroblanco.login_compose.ui.BotonModo
import com.alejandroblanco.login_compose.ui.LoginScreen
import com.alejandroblanco.login_compose.ui.WelcomeScreen
import com.alejandroblanco.login_compose.ui.theme.Login_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContent: aquí empieza Compose. Todo lo de dentro es interfaz declarativa.
        setContent {
            // empieza con el modo del movil, pero el boton lo puede cambiar
            val sistemaOscuro = isSystemInDarkTheme()
            var modoOscuro by rememberSaveable { mutableStateOf(sistemaOscuro) }

            // La app se dibuja bajo la barra de estado y la de navegación (pantalla completa).
            // Lo repetimos cada vez que cambia el modo para que los iconos de las barras se vean bien
            DisposableEffect(modoOscuro) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { modoOscuro },
                    navigationBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { modoOscuro },
                )
                onDispose { }
            }

            Login_composeTheme(darkTheme = modoOscuro) {
                App(
                    modoOscuro = modoOscuro,
                    onCambiarModo = { modoOscuro = !modoOscuro },
                )
            }
        }
    }
}

/**
 * Decide QUÉ pantalla se ve, usando un único estado: el nombre del usuario.
 *
 *   nombreUsuario == null   ->  nadie ha iniciado sesión  ->  mostramos el LOGIN
 *   nombreUsuario != null   ->  sesión iniciada           ->  mostramos la BIENVENIDA
 *
 * Cuando el login avisa de que el usuario acertó, guardamos su nombre en el
 * estado; Compose redibuja y cambia de pantalla solo. Al cerrar sesión hacemos
 * lo contrario: volvemos a poner el estado en null. No hace falta ninguna
 * librería de navegación para este ejemplo.
 *
 * ¿Por qué el formulario vuelve LIMPIO? Porque al cambiar a la bienvenida la
 * pantalla de login desaparece de la composición y su estado (lo que se había
 * escrito) se descarta. Al volver, Compose la crea de cero y los campos están vacíos.
 */
@Composable
private fun App(
    modoOscuro: Boolean,
    onCambiarModo: () -> Unit,
) {
    var nombreUsuario by rememberSaveable { mutableStateOf<String?>(null) }

    // Scaffold nos da el fondo del tema y calcula "innerPadding":
    // el espacio que ocupan las barras del sistema, para no taparnos con ellas.
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        // Box para poner el boton del modo encima de las pantallas, arriba a la derecha
        Box(modifier = Modifier.fillMaxSize()) {
        // AnimatedContent anima el cambio entre el login y la bienvenida:
        // una pantalla entra deslizando y la otra sale, las dos con fundido
        AnimatedContent(
            targetState = nombreUsuario,
            transitionSpec = {
                val sentido = if (targetState != null) 1 else -1   // 1 = entrar, -1 = cerrar sesión
                (slideInHorizontally(tween(500)) { ancho -> ancho * sentido } + fadeIn(tween(500))) togetherWith
                    (slideOutHorizontally(tween(500)) { ancho -> -ancho * sentido } + fadeOut(tween(300)))
            },
            label = "cambio de pantalla",
        ) { nombre ->
            if (nombre == null) {
                // onLoginCorrecto se ejecuta cuando el login acierta; "it" es el nombre recibido.
                LoginScreen(
                    onLoginCorrecto = { nombreUsuario = it },
                    modifier = modifier,
                )
            } else {
                // Pasamos el nombre como parámetro a la segunda pantalla.
                // onCerrarSesion se ejecuta al pulsar el botón: borramos el nombre y
                // el "if" de arriba vuelve a mostrar el login.
                WelcomeScreen(
                    nombre = nombre,
                    onCerrarSesion = { nombreUsuario = null },
                    modifier = modifier,
                )
            }
        }

        BotonModo(
            modoOscuro = modoOscuro,
            onCambiar = onCambiarModo,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(innerPadding)
                .padding(8.dp),
        )
        }
    }
}