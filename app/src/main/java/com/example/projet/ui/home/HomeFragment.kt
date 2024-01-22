package com.example.projet.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.databinding.FragmentHomeBinding
import com.example.projet.viewmodel.PlantsViewModel
import isNetworkAvailable
import kotlinx.coroutines.launch
import stopNetworkAvailabilityCheck

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val plantsViewModel: PlantsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        plantsViewModel.getAllPlants()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        lifecycleScope.launch {
            val welcomeTextView: TextView = binding.textHome
            welcomeTextView.text = "Welcome to the Plant List, Plant savvy!"

            // Set up RecyclerView
            val layoutManager = LinearLayoutManager(activity)
            val recyclerView: RecyclerView = binding.recyclerView

            recyclerView.layoutManager = layoutManager
            isNetworkAvailable(5000,{
                // DO SOMETHING HERE
                plantsViewModel.getAllPlants()
                plantsViewModel.plants.observe(viewLifecycleOwner) {
                    recyclerView.adapter = it?.let { it1 -> PlantAdapter(it1) }
                }
            },requireContext());
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        stopNetworkAvailabilityCheck()

        _binding = null
    }
}