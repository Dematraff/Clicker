package com.example.myapplicationkotlin.retrofit.api

import com.example.myapplicationkotlin.retrofit.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("products/1")
    suspend fun  getProductById(): Product
}