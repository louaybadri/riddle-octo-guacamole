package com.example.projet.network

import com.example.projet.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface PlantsApi {
    @GET("plants?token=h0nDnBToSQmqYBVxUJ84uBvxEz7FmuCy66eG1TU3B-I")
    fun getPlants(): Call<ApiResponse>
}