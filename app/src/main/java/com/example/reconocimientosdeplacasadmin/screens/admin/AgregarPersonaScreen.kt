package com.example.reconocimientosdeplacasadmin.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarPersonaScreen(
    onGuardar: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Estados para cada campo, agregando uno para "Número de Placa"
    val (numeroPlaca, setNumeroPlaca) = remember { mutableStateOf("") }
    val (nombre, setNombre) = remember { mutableStateOf("") }
    val (telefono, setTelefono) = remember { mutableStateOf("") }
    val (cedula, setCedula) = remember { mutableStateOf("") }
    val (tipoPersona, setTipoPersona) = remember { mutableStateOf("") }
    val (ubicacion, setUbicacion) = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Agregar Vehículo",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        // Contenedor principal para el formulario
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Campo para Número de Placa
            OutlinedTextField(
                value = numeroPlaca,
                onValueChange = setNumeroPlaca,
                label = { Text("Número de Placa") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = setNombre,
                label = { Text("Nombre Completo") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = telefono,
                onValueChange = setTelefono,
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = cedula,
                onValueChange = setCedula,
                label = { Text("Cédula / Identificación") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            // Componente para seleccionar el tipo de persona mediante dropdown
            TipoPersonaDropdown(
                selectedOption = tipoPersona,
                onOptionSelected = { setTipoPersona(it) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = ubicacion,
                onValueChange = setUbicacion,
                label = { Text("Ubicación (si aplica)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { onGuardar() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Guardar",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipoPersonaDropdown(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    // Lista de opciones
    val options = listOf("Residente", "Visitante")
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = { },
            label = { Text("Tipo de Persona") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                        contentDescription = "Mostrar menú"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgregarPersonaScreenPreview() {
    ReconocimientosDePlacasAdminTheme {
        AgregarPersonaScreen(onGuardar = { /* Acción de prueba */ })
    }
}
