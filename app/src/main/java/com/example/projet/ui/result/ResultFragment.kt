package com.example.projet.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.projet.databinding.FragmentResultBinding
import isNetworkAvailable
import stopNetworkAvailabilityCheck

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val resultViewModel: ResultViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val root: View = binding.root
        resultViewModel.initialize()
        isNetworkAvailable(5000,{
            resultViewModel.score.observe(viewLifecycleOwner) { resultData ->
            binding.textResult.text = "You correctly identified ${resultData.correctAnswers} plants out of ${resultData.totalAttempts} attempts!"
        }

            binding.resetButton.setOnClickListener {
                resultViewModel.resetScore()
            }
        },requireContext());
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}