package com.example.projet.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet.model.Plant
import com.example.projet.repository.PlantsRepository
import kotlinx.coroutines.launch

class PlantsViewModel : ViewModel() {
    private val plantsMutableLiveData = MutableLiveData<ArrayList<Plant>?>()
    private val plantsRepository = PlantsRepository
    val plants: LiveData<ArrayList<Plant>?> = plantsMutableLiveData

    init {
        getAllPlants()
    }

    //TODO Set this back to private after testing it.
    fun getAllPlants() {
        viewModelScope.launch {
            plantsMutableLiveData.value = plantsRepository.getPlants()
            plantsMutableLiveData.value?.forEach {
                Log.v("P", it.toString())
            }
        }
    }
}

