package com.example.projet.ui.riddle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projet.model.Plant
import com.example.projet.model.Riddle
import com.example.projet.viewmodel.PlantsViewModel

class RiddleViewModel : ViewModel() {
    private var correctAnswers = 0
    private var totalAttempts = 0
    private val _currentRiddle = MutableLiveData<Riddle>() // Riddle is a class representing your question
    val currentRiddle: LiveData<Riddle> = _currentRiddle
    private val _text = MutableLiveData<String>().apply {
        value = "Can you guess the plant ?"
    }
    val text: LiveData<String> = _text



    private val allPlants: List<Plant> = PlantsViewModel.plants.value ?: emptyList()


    fun setNextRiddle() {
        val plantOptions = allPlants.shuffled().take(3)
        val correctAnswer = plantOptions.random()

        _currentRiddle.value = Riddle(correctAnswer, plantOptions)
    }
}