package com.example.madlevel1task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.madlevel1task2.databinding.ActivityTruthTableBinding

private lateinit var binding: ActivityTruthTableBinding

class TruthTableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTruthTableBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}