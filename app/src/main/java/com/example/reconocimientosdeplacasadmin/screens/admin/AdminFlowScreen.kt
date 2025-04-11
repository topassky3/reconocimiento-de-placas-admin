package com.example.reconocimientosdeplacasadmin.ui.screens.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun AdminFlowScreen(modifier: Modifier = Modifier) {
    // Variable que gestiona la pantalla actual: "home", "agregar", "verEstado", "historico" o "login"
    var currentScreen by remember { mutableStateOf("home") }

    when (currentScreen) {
        "home" -> AdminHomeScreen(
            modifier = modifier,
            onAgregarPersona = { currentScreen = "agregar" },
            onVerPersonas = { currentScreen = "verEstado" },
            onConsultarHistoricos = { currentScreen = "historico" },
            onLogout = {
                // Aquí defines qué hacer al cerrar sesión, por ejemplo,
                // podrías cambiar a un estado "login" para mostrar la pantalla de login.
                currentScreen = "login"
            }
        )
        "agregar" -> AgregarPersonaScreen(
            modifier = modifier,
            onGuardar = { currentScreen = "home" }
        )
        "verEstado" -> VerEstadoVehiculoScreen(
            modifier = modifier,
            onVolver = { currentScreen = "home" }
        )
        "historico" -> ConsultaHistoricoPlacaScreen(
            modifier = modifier,
            onVolver = { currentScreen = "home" }
        )
        "login" -> {
            // Esta es la pantalla de login. La función LoginScreen está en otro paquete,
            // pero aquí la invocas para cerrar sesión y regresar.
            com.example.reconocimientosdeplacasadmin.ui.screens.login.LoginScreen(
                onLogin = { email, password ->
                    // Después de un login exitoso, se vuelve al flujo de administración.
                    currentScreen = "home"
                },
                onRecuperarContrasena = {
                    // Acción para recuperar contraseña.
                },
                modifier = modifier
            )
        }
    }
}
