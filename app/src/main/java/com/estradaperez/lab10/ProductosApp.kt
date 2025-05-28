package com.estradaperez.lab10

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.estradaperez.lab10.screens.ScreenProductoEditar
import com.estradaperez.lab10.screens.ScreenProductoEliminar
import com.estradaperez.lab10.screens.ScreenProductos
import com.estradaperez.lab10.service.ProductoApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosApp() {
    val urlBase = "http://192.168.165.135:8000/api/productos/"
    val retrofit = Retrofit.Builder().baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val servicio = retrofit.create(ProductoApiService::class.java)
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.padding(top = 40.dp),
        topBar = {
            TopAppBar(
                title = { Text("PRODUCTOS APP", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = { BotonFABProductos(navController) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = "productos"
                ) {
                    composable("productos") { ScreenProductos(navController, servicio) }
                    /// Dentro de NavHost en ProductosApp
                    composable("productoNuevo") {
                        ScreenProductoEditar(navController, servicio, null)
                    }
                    composable("productoVer/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })) {
                        val id = it.arguments!!.getInt("id")
                        ScreenProductoEditar(navController, servicio, id)
                    }
                    composable("productoDel/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })) {
                        val id = it.arguments!!.getInt("id")
                        ScreenProductoEliminar(navController, servicio, id)
                    }
                }
            }
        }
    )
}

@Composable
fun BotonFABProductos(navController: NavHostController) {
    val cbeState by navController.currentBackStackEntryAsState()
    val rutaActual = cbeState?.destination?.route
    if (rutaActual == "productos") {
        FloatingActionButton(
            containerColor = Color.Magenta,
            contentColor = Color.White,
            onClick = { navController.navigate("productoNuevo") }
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Agregar"
            )
        }
    }
}