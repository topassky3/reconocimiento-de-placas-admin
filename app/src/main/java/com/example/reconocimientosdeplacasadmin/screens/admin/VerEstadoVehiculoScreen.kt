package com.example.reconocimientosdeplacasadmin.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.reconocimientosdeplacasadmin.ui.theme.ReconocimientosDePlacasAdminTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerEstadoVehiculoScreen(
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Estado de entrada y validación
    var placa by remember { mutableStateOf("") }
    var validado by remember { mutableStateOf(false) }
    // Autorizado: true si la placa es "ABC-1234", false en caso contrario
    var autorizado by remember { mutableStateOf<Boolean?>(null) }
    // Opcional: si se desea, el admin puede activar la opción para modificar manualmente
    var overrideActive by remember { mutableStateOf(false) }
    var adminDenegador by remember { mutableStateOf("") }
    // Hora de reporte simulada para cuando el acceso es denegado
    val horaReporte = "10:30 AM"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Validación de Seguridad",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        // Contenedor principal
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = placa,
                onValueChange = { placa = it },
                label = { Text("Número de Placa") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    validado = true
                    autorizado = placa == "ABC-1234"
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Validar Ingreso",
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            if (validado) {
                if (autorizado == true) {
                    // Vehículo autorizado: muestra la información del contacto
                    Text(
                        text = "Estado: Autorizado",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Contacto del Vehículo:\nJuan Pérez - 555-1234",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    // Opción para que el admin anule el resultado (si lo requiere)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(text = "Modificar estado a Denegado:")
                        Spacer(modifier = Modifier.width(8.dp))
                        Switch(
                            checked = overrideActive,
                            onCheckedChange = { overrideActive = it },
                            colors = SwitchDefaults.colors()
                        )
                    }
                } else {
                    // Vehículo no autorizado: muestra la información de rechazo
                    Text(
                        text = "Estado: Denegado",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                // Independientemente del resultado, se muestran los datos adicionales siempre:
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = adminDenegador,
                    onValueChange = { adminDenegador = it },
                    label = { Text("Ingresa comentarios adicionales") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (autorizado == false) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Hora de reporte: $horaReporte",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Botón "Modificar" siempre visible para revalidar o ajustar la información
                Button(
                    onClick = {
                        // Reinicia la validación para permitir modificar los datos
                        validado = false
                        autorizado = null
                        overrideActive = false
                        adminDenegador = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Modificar",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            // Botón para volver al menú principal
            Button(
                onClick = onVolver,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Volver",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerEstadoVehiculoScreenPreview() {
    ReconocimientosDePlacasAdminTheme {
        VerEstadoVehiculoScreen(onVolver = { /* Acción dummy para preview */ })
    }
}
