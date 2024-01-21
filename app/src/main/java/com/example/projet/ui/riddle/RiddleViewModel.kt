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



    private val allPlants: List<Plant> = listOf(

        Plant(1, "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png?fbclid=IwAR2q_zQ2lZ2TY3x2xzSYVc7V9e-asxEuJcawPWUEXe5ka17-45IQDbn78EU", "Plant A"),
        Plant(2, "https://bs.plantnet.org/image/o/db3b1d70fed25a0c1a16ba5bf9ee79d980a510e9", "Plant B"),
        Plant(3, "https://bs.plantnet.org/image/o/db3b1d70fed25a0c1a16ba5bf9ee79d980a510e9", "Plant C"),
        Plant(1, "https://bs.plantnet.org/image/o/db3b1d70fed25a0c1a16ba5bf9ee79d980a510e9", "Plant AA"),
        Plant(2, "https://bs.plantnet.org/image/o/db3b1d70fed25a0c1a16ba5bf9ee79d980a510e9", "Plant BB"),
        Plant(3, "https://bs.plantnet.org/image/o/db3b1d70fed25a0c1a16ba5bf9ee79d980a510e9", "Plant CC"),
        Plant(1, "https://bs.plantnet.org/image/o/db3b1d70fed25a0c1a16ba5bf9ee79d980a510e9", "Plant AAA"),
        Plant(2, "https://bs.plantnet.org/image/o/db3b1d70fed25a0c1a16ba5bf9ee79d980a510e9", "Plant BBB"),
        Plant(3, "https://bs.plantnet.org/image/o/db3b1d70fed25a0c1a16ba5bf9ee79d980a510e9", "Plant CCC"),
        Plant(1, "https://bs.plantnet.org/image/o/db3b1d70fed25a0c1a16ba5bf9ee79d980a510e9", "Plant AAAAA"),
        Plant(2, "https://bs.plantnet.org/image/o/db3b1d70fed25a0c1a16ba5bf9ee79d980a510e9", "Plant BBBBB"),
        Plant(3, "https://bs.plantnet.org/image/o/db3b1d70fed25a0c1a16ba5bf9ee79d980a510e9", "Plant CCCCC"),
    )


    fun setNextRiddle() {
        val plantOptions = allPlants.shuffled().take(3)
        val correctAnswer = plantOptions.random()

        _currentRiddle.value = Riddle(correctAnswer, plantOptions)
    }
}