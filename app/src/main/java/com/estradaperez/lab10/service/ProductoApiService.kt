package com.estradaperez.lab10.service

import com.estradaperez.lab10.model.ProductoModel
import retrofit2.Response
import retrofit2.http.*

interface ProductoApiService {
    @GET(".")
    suspend fun selectProductos(): ArrayList<ProductoModel>

    @GET("{id}/")
    suspend fun selectProducto(@Path("id") id: String): Response<ProductoModel>

    @Headers("Content-Type: application/json")
    @POST(".")
    suspend fun insertProducto(@Body producto: ProductoModel): Response<ProductoModel>

    @PUT("{id}/")
    suspend fun updateProducto(@Path("id") id: String, @Body producto: ProductoModel): Response<ProductoModel>

    @DELETE("{id}/")
    suspend fun deleteProducto(@Path("id") id: String): Response<ProductoModel>
}