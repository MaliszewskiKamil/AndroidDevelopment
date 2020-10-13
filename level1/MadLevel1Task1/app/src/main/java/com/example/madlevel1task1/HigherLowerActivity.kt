package com.example.madlevel1task1

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madlevel1task1.databinding.ActivityHigherLowerBinding

private lateinit var binding: ActivityHigherLowerBinding

class MainActivity : AppCompatActivity() {

    private var currentThrow: Int = 1
    private var lastThrow: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHigherLowerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.equalBtn.setOnClickListener{
            updateUi()
        }
    }

    private fun initViews(){
        updateUi()
    }

    private fun updateUi() {
        binding.lastThrowTv.text = getString(R.string.last_throw, lastThrow)
        var imageName = "R.drawable.dice"
        imageName += lastThrow
        binding.diceRollIv.setImageResource(resources.getIdentifier(imageName, "drawable", packageName))
    }

    private fun rollDice(){
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUi()
    }


}