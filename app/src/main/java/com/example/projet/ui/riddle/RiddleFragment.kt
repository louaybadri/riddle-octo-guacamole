    package com.example.projet.ui.riddle

    import android.graphics.Color
    import android.os.Bundle
    import android.os.Handler
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.Button
    import android.widget.ImageView
    import android.widget.RadioButton
    import android.widget.TextView
    import androidx.fragment.app.Fragment
    import androidx.fragment.app.activityViewModels
    import androidx.lifecycle.Observer
    import androidx.lifecycle.ViewModelProvider
    import androidx.lifecycle.lifecycleScope
    import com.example.projet.databinding.FragmentDashboardBinding
    import com.bumptech.glide.Glide
    import com.example.projet.model.Plant
    import com.example.projet.model.ResultData
    import com.example.projet.model.Riddle
    import com.example.projet.ui.result.ResultViewModel
    import kotlinx.coroutines.delay
    import kotlinx.coroutines.launch

    class DashboardFragment : Fragment() {

        private var _binding: FragmentDashboardBinding? = null

        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!
        private val resultViewModel: ResultViewModel by activityViewModels()
        private var correctAnswers = 0


        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            val riddleViewModel =
                ViewModelProvider(this).get(RiddleViewModel::class.java)
            _binding = FragmentDashboardBinding.inflate(inflater, container, false)
            val root: View = binding.root

            val textView: TextView = binding.textDashboard
            riddleViewModel.text.observe(viewLifecycleOwner) {
                textView.text = it
            }
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
                    lifecycleScope.launch {
                        val selectedRadioButton =
                            binding.radioGroup.findViewById<RadioButton>(checkedId)
                        selectedRadioButton?.isChecked = false


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
                }









            })

            riddleViewModel.setNextRiddle()

            return root
        }
        private fun resetRadioButtons() {
            for (i in 0 until binding.radioGroup.childCount) {
                val radioButton = binding.radioGroup.getChildAt(i) as? RadioButton
                radioButton?.isEnabled = true
//                radioButton?.isChecked = false
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
            _binding = null
        }
    }