package com.estradaperez.lab10.screens

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.estradaperez.lab10.model.ProductoModel
import com.estradaperez.lab10.service.ProductoApiService

@Composable
fun ScreenProductos(navController: NavHostController, servicio: ProductoApiService) {
    var listaProductos = remember { mutableStateListOf<ProductoModel>() }
    LaunchedEffect(Unit) {
        val listado = servicio.selectProductos()
        listaProductos.clear()
        listaProductos.addAll(listado)
    }

    LazyColumn {
        item {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("ID", fontSize = 16.sp, modifier = Modifier.weight(0.1f))
                Text("Nombre", fontSize = 16.sp, modifier = Modifier.weight(0.4f))
                Text("Precio", fontSize = 16.sp, modifier = Modifier.weight(0.2f))
                Text("Acción", fontSize = 16.sp, modifier = Modifier.weight(0.3f))
            }
        }
        items(listaProductos) { producto ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("${producto.id}", modifier = Modifier.weight(0.1f))
                Text(producto.nombre, modifier = Modifier.weight(0.4f))
                Text(producto.precio, modifier = Modifier.weight(0.2f))
                IconButton(
                    onClick = {
                        navController.navigate("productoVer/${producto.id}")
                        Log.e("PRODUCTO-VER", "ID = ${producto.id}")
                    },
                    modifier = Modifier.weight(0.15f)
                ) {
                    Icon(Icons.Outlined.Edit, contentDescription = "Editar")
                }
                IconButton(
                    onClick = {
                        navController.navigate("productoDel/${producto.id}")
                        Log.e("PRODUCTO-DEL", "ID = ${producto.id}")
                    },
                    modifier = Modifier.weight(0.15f)
                ) {
                    Icon(Icons.Outlined.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}