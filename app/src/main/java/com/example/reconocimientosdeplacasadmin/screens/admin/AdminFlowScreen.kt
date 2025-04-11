package com.example.reconocimientosdeplacasadmin.ui.screens.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun AdminFlowScreen(modifier: Modifier = Modifier) {
    // Variable que gestiona la pantalla actual ("home", "agregar" o "verEstado")
    var currentScreen by remember { mutableStateOf("home") }

    when (currentScreen) {
        "home" -> AdminHomeScreen(
            modifier = modifier,
            onAgregarPersona = { currentScreen = "agregar" },
            onVerPersonas = { currentScreen = "verEstado" },
            onConsultarHistoricos = {
                // Acción para "Consultar históricos" (puedes implementarlo más adelante)
            }
        )
        "agregar" -> AgregarPersonaScreen(
            modifier = modifier,
            onGuardar = {
                // Después de guardar, volvemos al menú principal.
                currentScreen = "home"
            }
        )
        "verEstado" -> VerEstadoVehiculoScreen(
            modifier = modifier,
            onVolver = { currentScreen = "home" }  // Botón "Volver" para regresar al menú.
        )
    }
}
