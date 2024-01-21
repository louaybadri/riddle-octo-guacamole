package com.example.projet.repository

import android.util.Log
import com.example.projet.model.Plant
import com.example.projet.network.PlantsApiRetrofitHelper

object PlantsRepository {
    suspend fun getPlants(): ArrayList<Plant>? {
        return try {
            val response = PlantsApiRetrofitHelper.retrofitService.getPlantsResponse()
            if (response.isSuccessful) {
                Log.v("RS", "Response succeeded")
                return response.body()?.data as ArrayList<Plant>
            } else {
                Log.e("RF", "Response Failed")
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}