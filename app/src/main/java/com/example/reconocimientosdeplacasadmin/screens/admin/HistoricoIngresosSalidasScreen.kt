package com.example.reconocimientosdeplacasadmin.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.reconocimientosdeplacasadmin.ui.theme.ReconocimientosDePlacasAdminTheme

// Modelo de datos para un registro del histórico.
data class HistorialRecord(
    val placa: String,
    val timestamp: String,
    val responsable: String,
    val evento: String   // "Ingreso" o "Salida"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaHistoricoPlacaScreen(
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Estado para filtrar por placa.
    var busquedaPlaca by remember { mutableStateOf("") }
    // Datos de ejemplo simulados; en una app real se obtendrían desde la base de datos.
    val historicoCompleto = remember {
        listOf(
            HistorialRecord("ABC-1234", "2025-04-10 08:15", "Guardia Mario", "Ingreso"),
            HistorialRecord("ABC-1234", "2025-04-10 18:45", "Guardia Mario", "Salida"),
            HistorialRecord("XYZ-5678", "2025-04-10 08:45", "Guardia Juan", "Ingreso"),
            HistorialRecord("XYZ-5678", "2025-04-10 17:30", "Guardia Juan", "Salida"),
            HistorialRecord("DEF-9012", "2025-04-10 09:30", "Guardia Ana", "Ingreso")
        )
    }
    // Filtrar la lista por placa (si la búsqueda está vacía se muestran todos)
    val historicoFiltrado = historicoCompleto.filter { it.placa.contains(busquedaPlaca, ignoreCase = true) }

    // Estados para el nuevo registro
    var nuevoTimestamp by remember { mutableStateOf("") }
    var nuevoResponsable by remember { mutableStateOf("") }
    var nuevoEvento by remember { mutableStateOf("Ingreso") } // Opciones: "Ingreso" o "Salida"
    // Estado de confirmación de guardado (simulado)
    var registroGuardado by remember { mutableStateOf(false) }
    val mensajeConfirmacion = "Registro guardado correctamente."

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Consultar Histórico por Placa",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        // La pantalla se divide en tres secciones: búsqueda, nuevo registro y listado.
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Sección de Búsqueda.
            Column {
                OutlinedTextField(
                    value = busquedaPlaca,
                    onValueChange = { busquedaPlaca = it },
                    label = { Text("Buscar Placa") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            // Sección para agregar nuevo registro (siempre visible, primero que el listado).
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Nuevo Registro",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = nuevoTimestamp,
                    onValueChange = { nuevoTimestamp = it },
                    label = { Text("Fecha y Hora") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = nuevoResponsable,
                    onValueChange = { nuevoResponsable = it },
                    label = { Text("Responsable de Autorización") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Selección de tipo de evento mediante radio buttons.
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Tipo de Evento:",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = nuevoEvento == "Ingreso",
                            onClick = { nuevoEvento = "Ingreso" },
                            colors = RadioButtonDefaults.colors()
                        )
                        Text(text = "Ingreso")
                        Spacer(modifier = Modifier.width(16.dp))
                        RadioButton(
                            selected = nuevoEvento == "Salida",
                            onClick = { nuevoEvento = "Salida" },
                            colors = RadioButtonDefaults.colors()
                        )
                        Text(text = "Salida")
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        // Aquí se simula el guardado. En una app real se enviaría el registro a la base de datos.
                        registroGuardado = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Guardar Registro",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                if (registroGuardado) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = mensajeConfirmacion,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            // Sección de Listado de históricos.
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(historicoFiltrado) { record ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "Placa: ${record.placa}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Evento: ${record.evento}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Fecha/Hora: ${record.timestamp}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Autorizado por: ${record.responsable}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
            // Botón para volver al menú principal.
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))
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
}

@Preview(showBackground = true)
@Composable
fun ConsultaHistoricoPlacaScreenPreview() {
    ReconocimientosDePlacasAdminTheme {
        ConsultaHistoricoPlacaScreen(onVolver = { /* Acción dummy para preview */ })
    }
}
