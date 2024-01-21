package com.example.projet.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projet.databinding.FragmentHomeBinding
import com.example.projet.model.Plant

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val fakeUrl = "https://www.codelikethewind.org/content/images/size/w2000/2022/05/hello_world.png"

    private val plantList = arrayListOf<Plant>(
        Plant(1, fakeUrl, "Plant One"),
        Plant(2, fakeUrl, "Plant Two"),
        Plant(1, fakeUrl, "Plant One"),
        Plant(2, fakeUrl, "Plant Two"),
        Plant(1, fakeUrl, "Plant One"),
        Plant(2, fakeUrl, "Plant Two"),
        Plant(1, fakeUrl, "Plant One"),
        Plant(2, fakeUrl, "Plant Two"),
        Plant(1, fakeUrl, "Plant One"),
        Plant(2, fakeUrl, "Plant Two"),
        Plant(1, fakeUrl, "Plant One"),
        Plant(2, fakeUrl, "Plant Two"),
        // Add more plant instances as needed
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        val welcomeTextView: TextView = binding.textHome
        val recyclerView: RecyclerView = binding.recyclerView

        // Set content or handle actions as needed
        welcomeTextView.text = "Welcome to the Riddle App!"

        // Set up RecyclerView
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        val adapter = PlantAdapter(plantList)
        recyclerView.adapter = adapter

        return root
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}