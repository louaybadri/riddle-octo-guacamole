package com.example.projet.network


import com.example.projet.BuildConfig
import com.example.projet.interceptor.ApiKeyInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PlantsApiRetrofitHelper {
    private const val baseUrl = BuildConfig.BASE_URL
    private const val apiKey = BuildConfig.API_KEY


    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor(apiKey))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: PlantsApi by lazy { retrofit.create(PlantsApi::class.java) }
}