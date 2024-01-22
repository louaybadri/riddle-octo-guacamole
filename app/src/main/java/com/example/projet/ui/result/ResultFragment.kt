package com.example.projet.ui.result

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.projet.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val resultViewModel: ResultViewModel by activityViewModels()


        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val root: View = binding.root

        resultViewModel.score.observe(viewLifecycleOwner) { resultData ->
            binding.textResult.text = "You correctly identified ${resultData.correctAnswers} plants out of ${resultData.totalAttempts} attempts!"
        }

        binding.resetButton.setOnClickListener {
            resultViewModel.resetScore()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}