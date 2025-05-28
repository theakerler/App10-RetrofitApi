package com.estradaperez.lab10.model

import com.google.gson.annotations.SerializedName

data class ProductoModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("precio")
    val precio: String,
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("pub_date")
    val pubDate: String,
    @SerializedName("imagen")
    val imagen: String,
    @SerializedName("categoria")
    val categoria: Int // id de la categoría
)