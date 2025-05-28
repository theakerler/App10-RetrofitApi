package com.estradaperez.lab10.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.estradaperez.lab10.service.ProductoApiService
import kotlinx.coroutines.launch

@Composable
fun ScreenProductoEliminar(
    navController: NavHostController,
    servicio: ProductoApiService,
    productoId: Int
) {
    val scope = rememberCoroutineScope()
    AlertDialog(
        onDismissRequest = { navController.popBackStack() },
        title = { Text("Eliminar producto") },
        text = { Text("¿Estás seguro de eliminar este producto?") },
        confirmButton = {
            Button(onClick = {
                scope.launch {
                    servicio.deleteProducto(productoId.toString())
                    navController.popBackStack()
                }
            }) { Text("Eliminar") }
        },
        dismissButton = {
            OutlinedButton(onClick = { navController.popBackStack() }) { Text("Cancelar") }
        }
    )
}