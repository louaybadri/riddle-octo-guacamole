package com.example.projet.network



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PlantsApiRetrofitHelper {
    private const val baseUrl ="https://trefle.io/api/v1/"
    /**
     * The Retrofit object with Gson converter.
     */
    private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        // we need to add converter factory to
        // convert JSON object to Java object
        .build()
    /**
     * A public Api object that exposes the lazy-initialized Retrofit service
     */
    val retrofitService : PlantsApi by lazy { retrofit.create(PlantsApi::class.java) }
}