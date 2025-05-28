package com.estradaperez.lab10.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.estradaperez.lab10.model.ProductoModel
import com.estradaperez.lab10.service.ProductoApiService
import kotlinx.coroutines.launch

@Composable
fun ScreenProductoEditar(
    navController: NavHostController,
    servicio: ProductoApiService,
    productoId: Int?
) {
    val scope = rememberCoroutineScope()
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }

    // Si es edición, carga los datos
    LaunchedEffect(productoId) {
        if (productoId != null && productoId != 0) {
            val response = servicio.selectProducto(productoId.toString())
            response.body()?.let {
                nombre = it.nombre
                precio = it.precio
                stock = it.stock.toString()
                imagen = it.imagen
                categoria = it.categoria.toString()
            }
        }
    }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") })
        OutlinedTextField(value = stock, onValueChange = { stock = it }, label = { Text("Stock") })
        OutlinedTextField(value = imagen, onValueChange = { imagen = it }, label = { Text("Imagen") })
        OutlinedTextField(value = categoria, onValueChange = { categoria = it }, label = { Text("Categoría (ID)") })
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            scope.launch {
                val producto = ProductoModel(
                    id = productoId ?: 0,
                    nombre = nombre,
                    precio = precio,
                    stock = stock.toIntOrNull() ?: 0,
                    pubDate = "", // Puedes omitir o manejar pubDate según tu lógica
                    imagen = imagen,
                    categoria = categoria.toIntOrNull() ?: 0
                )
                if (productoId == null || productoId == 0) {
                    servicio.insertProducto(producto)
                } else {
                    servicio.updateProducto(productoId.toString(), producto)
                }
                navController.popBackStack()
            }
        }) {
            Text(if (productoId == null || productoId == 0) "Añadir" else "Guardar")
        }
    }
}