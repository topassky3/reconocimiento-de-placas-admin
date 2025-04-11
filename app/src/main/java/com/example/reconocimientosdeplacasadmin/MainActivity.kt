package com.example.reconocimientosdeplacasadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.reconocimientosdeplacasadmin.ui.screens.admin.AdminFlowScreen
import com.example.reconocimientosdeplacasadmin.ui.theme.ReconocimientosDePlacasAdminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReconocimientosDePlacasAdminTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AdminFlowScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    ReconocimientosDePlacasAdminTheme {
        AdminFlowScreen(modifier = Modifier.fillMaxSize())
    }
}
