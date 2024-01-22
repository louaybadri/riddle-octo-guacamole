package com.example.projet.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.databinding.FragmentHomeBinding
import com.example.projet.dummy_data.DUMMY
import com.example.projet.ui.utils.ConnectivityUtils
import com.example.projet.viewmodel.PlantsViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val plantsViewModel: PlantsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        refresh(5000);

        return root
    }
    private val handler = Handler()
    private fun refresh(millisecton: Long) {
        val runnable = Runnable {
            if (ConnectivityUtils.isInternetAvailable(requireContext())) {
                // DO SOMETHING HERE
                val welcomeTextView: TextView = binding.textHome
                welcomeTextView.text = "Welcome to the Plant List, Plant savvy!"
                val recyclerView: RecyclerView = binding.recyclerView
                // Set up RecyclerView
                val layoutManager = LinearLayoutManager(activity)
                recyclerView.layoutManager = layoutManager
                plantsViewModel.plants.observe(viewLifecycleOwner) {
                    recyclerView.adapter = it?.let { it1 -> PlantAdapter(it1) }
                }
            } else {
                ConnectivityUtils.handleNoInternetConnection(requireContext())
                handler.postDelayed({ refresh(millisecton) }, millisecton)
            }
        }
 runnable.run()}


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}