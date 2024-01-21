package com.example.projet.ui.riddle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projet.model.Plant
import com.example.projet.model.Riddle

class RiddleViewModel : ViewModel() {
    private var correctAnswers = 0
    private var totalAttempts = 0
    private val _currentRiddle = MutableLiveData<Riddle>() // Riddle is a class representing your question
    val currentRiddle: LiveData<Riddle> = _currentRiddle
    private val _text = MutableLiveData<String>().apply {
        value = "Can you guess the plant ?"
    }
    val text: LiveData<String> = _text
    private val _plant = MutableLiveData<Plant>().apply {
    val dummyPlant = Plant(id = 1, image_url = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", common_name = "Dummy Plant")
        value=dummyPlant

    }

    val plant: LiveData<Plant> = _plant

    private val allPlants: List<Plant> = listOf(

        Plant(1, "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", "Plant A"),
        Plant(2, "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", "Plant B"),
        Plant(3, "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", "Plant C"),
        Plant(1, "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", "Plant AA"),
        Plant(2, "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", "Plant BB"),
        Plant(3, "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", "Plant CC"),
        Plant(1, "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", "Plant AAA"),
        Plant(2, "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", "Plant BBB"),
        Plant(3, "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", "Plant CCC"),
        Plant(1, "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", "Plant AAAAA"),
        Plant(2, "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", "Plant BBBBB"),
        Plant(3, "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", "Plant CCCCC"),
    )


    fun setNextRiddle() {
        val plantOptions = allPlants.shuffled().take(3)
        val correctAnswer = plantOptions.random()

        _currentRiddle.value = Riddle(correctAnswer, plantOptions)
    }
}