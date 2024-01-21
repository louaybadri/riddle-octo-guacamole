package com.example.projet.network

import com.example.projet.model.PlantsResponse
import retrofit2.Response
import retrofit2.http.GET

interface PlantsApi {
    @GET("plants")
    suspend fun getPlantsResponse(): Response<PlantsResponse>
}