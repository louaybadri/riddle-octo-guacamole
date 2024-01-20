package com.example.projet.ui.home
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide
import com.example.projet.R

import com.example.projet.model.Plant;

import java.util.List;

class PlantAdapter(private val plantList:ArrayList<Plant>) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plant, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = plantList[position]

        // Load image from URL using Picasso
//        Picasso.get().load(plant.image_url).into(holder.plantImageView)
        Glide.with(holder.plantImageView.context).load(plant.image_url).into(holder.plantImageView)

        // Set common name text
        holder.commonNameTextView.text = plant.common_name
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plantImageView: ImageView = itemView.findViewById(R.id.plantImageView)
        val commonNameTextView: TextView = itemView.findViewById(R.id.commonNameTextView)
    }
}
