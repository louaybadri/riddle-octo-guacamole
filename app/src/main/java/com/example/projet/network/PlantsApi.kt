package com.example.projet.network

import com.example.projet.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface PlantsApi {
    @GET("plants")
    fun getPlants(): Call<ApiResponse>
}