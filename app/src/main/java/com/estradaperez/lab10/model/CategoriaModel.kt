package com.estradaperez.lab10.model

import com.google.gson.annotations.SerializedName

data class CategoriaModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("imagen")
    val imagen: String
)