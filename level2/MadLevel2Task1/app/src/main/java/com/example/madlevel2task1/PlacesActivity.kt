package com.example.madlevel2task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.madlevel2task1.adapters.PlaceAdapter
import com.example.madlevel2task1.data.Place
import com.example.madlevel2task1.databinding.ActivityPlacesBinding

private lateinit var binding: ActivityPlacesBinding
private val places = arrayListOf<Place>()
private val placesAdapter = PlaceAdapter(places)

class PlacesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {

        binding.placesRv.adapter = placesAdapter
        binding.placesRv.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)

        for(i in Place.PLACE_NAMES.indices) {
            places.add(Place(Place.PLACE_NAMES[i], Place.PLACE_RES_DRAWABLE_IDS[i]))
        }
        placesAdapter.notifyDataSetChanged()

    }
}