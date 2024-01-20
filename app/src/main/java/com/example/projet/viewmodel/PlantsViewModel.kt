package com.example.projet.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projet.model.ApiResponse
import com.example.projet.model.Plant
import com.example.projet.network.PlantsApiRetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlantsViewModel(): ViewModel() {
    private val plantsApiResponse = MutableLiveData<ArrayList<Plant>>()
    val plants: LiveData<ArrayList<Plant>> = plantsApiResponse

    init{
        getAllPlants()
    }

    fun getAllPlants() {
        PlantsApiRetrofitHelper.retrofitService.getPlants().enqueue(object: Callback<ApiResponse>{
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>){
                if(response.isSuccessful){
                    Log.v("RS", "Response is successful")
                    plantsApiResponse.value = response.body()?.data as ArrayList<Plant>?
                    plantsApiResponse.value!!.forEach{
                        Log.v("P", it.toString())
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("RF", t.message.toString())
            }
        })
    }
}