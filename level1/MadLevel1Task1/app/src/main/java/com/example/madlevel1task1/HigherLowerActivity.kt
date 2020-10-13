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
        initViews()

    }

    private fun initViews(){
        binding.higherBtn.setOnClickListener{
            onHigherClick()
        }

        binding.equalBtn.setOnClickListener{
            onEqualClick()
        }

        binding.lowerBtn.setOnClickListener{
            onLowerClick()
        }

        updateUi()
    }

    private fun updateUi() {
        binding.lastThrowTv.text = getString(R.string.last_throw, lastThrow)
        binding.diceRollIv.setImageResource(resources.getIdentifier(getString(R.string.get_image, currentThrow), "drawable", packageName))
    }

    private fun rollDice(){
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUi()
    }

    private fun onAnswerCorrect(){
        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
    }
    private fun onAnswerIncorrect(){
        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_LONG).show()
    }

    private fun onHigherClick(){
        rollDice()
        if(currentThrow>lastThrow){
            onAnswerCorrect()
        } else {
            onAnswerIncorrect()
        }
    }

    private fun onEqualClick(){
        rollDice()
        if(currentThrow == lastThrow){
            onAnswerCorrect()
        } else {
            onAnswerIncorrect()
        }
    }

    private fun onLowerClick(){
        rollDice()
        if(currentThrow<lastThrow){
            onAnswerCorrect()
        } else {
            onAnswerIncorrect()
        }
    }

}