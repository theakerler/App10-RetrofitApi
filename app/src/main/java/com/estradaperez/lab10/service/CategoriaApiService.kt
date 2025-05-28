package com.estradaperez.lab10.service

import com.estradaperez.lab10.model.CategoriaModel
import retrofit2.Response
import retrofit2.http.*

interface CategoriaApiService {
    @GET(".")
    suspend fun selectCategorias(): ArrayList<CategoriaModel>

    @GET("{id}/")
    suspend fun selectCategoria(@Path("id") id: String): Response<CategoriaModel>

    @Headers("Content-Type: application/json")
    @POST(".")
    suspend fun insertCategoria(@Body categoria: CategoriaModel): Response<CategoriaModel>

    @PUT("{id}/")
    suspend fun updateCategoria(@Path("id") id: String, @Body categoria: CategoriaModel): Response<CategoriaModel>

    @DELETE("{id}/")
    suspend fun deleteCategoria(@Path("id") id: String): Response<CategoriaModel>
}