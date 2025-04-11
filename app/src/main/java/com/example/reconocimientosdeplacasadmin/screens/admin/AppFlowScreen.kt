package com.example.reconocimientosdeplacasadmin.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.reconocimientosdeplacasadmin.ui.screens.admin.AdminFlowScreen
import com.example.reconocimientosdeplacasadmin.ui.screens.login.LoginScreen

@Composable
fun AppFlowScreen(modifier: Modifier = Modifier) {
    // "login" es la pantalla inicial; luego, al iniciar sesión exitosamente,
    // se cambia a "admin".
    var currentScreen by remember { mutableStateOf("login") }

    when(currentScreen) {
        "login" -> LoginScreen(
            modifier = modifier,
            onLogin = { email, password ->
                // Aquí se simula el login exitoso (en una app real se haría validación).
                // Si el login es correcto, se cambia la pantalla a "admin".
                currentScreen = "admin"
            },
            onRecuperarContrasena = {
                // Puedes navegar a una pantalla de recuperación, o mostrar un diálogo.
            }
        )
        "admin" -> AdminFlowScreen(modifier = modifier)
    }
}
