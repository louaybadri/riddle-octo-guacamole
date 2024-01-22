package com.example.projet.ui.riddle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projet.model.Plant
import com.example.projet.model.Riddle
import com.example.projet.viewmodel.PlantsViewModel

class RiddleViewModel : ViewModel() {
    private val _currentRiddle =
        MutableLiveData<Riddle>() // Riddle is a class representing your question
    val currentRiddle: LiveData<Riddle> = _currentRiddle


    private var allPlants: List<Plant> = PlantsViewModel.plants.value ?: emptyList()

    fun setNextRiddle() {
        if (allPlants.isNotEmpty()) {

            println(allPlants.size)
            val plantOptions = allPlants.shuffled().take(3)
            val correctAnswer = plantOptions.random()

            _currentRiddle.value = Riddle(correctAnswer, plantOptions)
        }
    }
}