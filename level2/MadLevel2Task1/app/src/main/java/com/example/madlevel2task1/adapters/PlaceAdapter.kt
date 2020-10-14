package com.example.madlevel2task1.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task1.data.Place
import com.example.madlevel2task1.databinding.ItemPlaceBinding

class PlaceAdapter(private val places: List<Place>) :

    RecyclerView.Adapter<PlaceAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPlaceBinding.bind(itemView)

        fun databind(place: Place){
            binding.placeNameTv.text = place.name
            binding.placeIv.setImageResource(place.imageResId)

        }
    }


}
