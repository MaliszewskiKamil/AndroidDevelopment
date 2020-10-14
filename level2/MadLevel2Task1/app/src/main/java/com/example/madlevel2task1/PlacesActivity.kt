package com.example.madlevel2task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.madlevel2task1.databinding.ActivityPlacesBinding

private lateinit var binding: ActivityPlacesBinding

class PlacesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}