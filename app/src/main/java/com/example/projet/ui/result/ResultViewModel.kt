package com.example.projet.ui.result

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projet.model.ResultData


class ResultViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("ResultPreferences", Context.MODE_PRIVATE)

    private val _score = MutableLiveData<ResultData>()
    init {
        // Retrieve previous results from SharedPreferences
        val correctAnswers = sharedPreferences.getInt("correctAnswers", 0)
        val totalAttempts = sharedPreferences.getInt("totalAttempts", 0)
        _score.value = ResultData(correctAnswers, totalAttempts)
    }



    val score: LiveData<ResultData> = _score









    fun setScore(resultData: ResultData) {
        // Only update if the new score is different
        if (_score.value != resultData) {
            // Update LiveData
            _score.value = resultData

            // Update SharedPreferences
            sharedPreferences.edit().apply {
                putInt("correctAnswers", resultData.correctAnswers)
                putInt("totalAttempts", resultData.totalAttempts)
                apply()
            }
        }
    }

    fun updateScore(correct: Boolean) {
        val currentScore = _score.value ?: ResultData(0, 0)

        val newScore = if (correct) {
            ResultData(currentScore.correctAnswers + 1, currentScore.totalAttempts + 1)
        } else {
            ResultData(currentScore.correctAnswers, currentScore.totalAttempts + 1)
        }

        // Update the score using the setScore function
        setScore(newScore)
    }
    fun resetScore() {
        setScore(ResultData(0, 0))
    }
}