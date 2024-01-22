package com.example.projet.ui.riddle

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.projet.R
import com.example.projet.databinding.FragmentRiddleBinding
import com.example.projet.ui.result.ResultViewModel
import com.example.projet.viewmodel.PlantsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import isNetworkAvailable
import stopNetworkAvailabilityCheck

class DashboardFragment : Fragment() {

    private var _binding: FragmentRiddleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val resultViewModel: ResultViewModel by viewModels()
    private val riddleViewModel: RiddleViewModel by viewModels()
    private val plantsViewModel: PlantsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRiddleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard

        textView.text = "Network is not available, please check your connection and get back to Home page"
        binding.plantImage.visibility = View.GONE
        binding.radioGroup.visibility = View.GONE

       isNetworkAvailable(5000,{
           textView.text = "Can you guess the plant ?"
           val nextButton: Button = binding.button
           nextButton.setOnClickListener {
               lifecycleScope.launch {
                   // Delay for a short duration to avoid rapid clicks
                   delay(300)
                       riddleViewModel.setNextRiddle()
                       resetRadioButtons()
                       nextButton.visibility = View.GONE


               }
           }
           riddleViewModel.currentRiddle.observe(viewLifecycleOwner, Observer { riddle ->

                   resetRadioButtons()
                   Glide.with(this)
                       .load(riddle.correctAnswer.image_url)
                       .into(binding.plantImage)

                   val shuffledOptions = riddle.options.shuffled()
                   binding.radioButton.text = shuffledOptions[0].common_name
                   binding.radioButton2.text = shuffledOptions[1].common_name
                   binding.radioButton3.text = shuffledOptions[2].common_name
                   binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->

                           val selectedRadioButton =
                               binding.radioGroup.findViewById<RadioButton>(checkedId)
                           val isCorrect = selectedRadioButton?.text == riddle.correctAnswer.common_name
                           resultViewModel.updateScore(isCorrect)
                           if (isCorrect) {
                               selectedRadioButton?.setTextColor(Color.GREEN)

                           } else {
                               selectedRadioButton?.setTextColor(Color.RED)
                           }
                           disableRadioGroupExcept(checkedId)
                           binding.button.visibility = View.VISIBLE
                   }

           })
           binding.plantImage.visibility = View.VISIBLE
           binding.radioGroup.visibility = View.VISIBLE
           riddleViewModel.setNextRiddle()

       },requireContext());


        return root
    }
    private fun resetRadioButtons() {
        for (i in 0 until binding.radioGroup.childCount) {
            val radioButton = binding.radioGroup.getChildAt(i) as? RadioButton
            radioButton?.isEnabled = true
                radioButton?.isChecked = false
            radioButton?.setTextColor(Color.BLACK)
        }
    }

    private fun disableRadioGroupExcept(exceptId: Int) {
        for (i in 0 until binding.radioGroup.childCount) {
            val radioButton = binding.radioGroup.getChildAt(i) as? RadioButton
            radioButton?.isEnabled = radioButton?.id == exceptId
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        stopNetworkAvailabilityCheck()
        _binding = null
    }
}