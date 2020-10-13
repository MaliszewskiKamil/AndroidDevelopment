package com.example.madlevel1task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madlevel1task2.databinding.ActivityTruthTableBinding

private lateinit var binding: ActivityTruthTableBinding

class TruthTableActivity : AppCompatActivity() {

    private var points: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTruthTableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView(){
        binding.submitBtn.setOnClickListener{
            onButtonClick()
        }
    }

    private fun resetPoints(){
        points = 0
    }

    private fun showToastWithPoints(){
        Toast.makeText(this, getString(R.string.correct_answers, points), Toast.LENGTH_LONG).show()
    }

    private fun checkAnswers(){
        if(binding.firstEv.text.toString() == "T")
            points++
        if(binding.secondEv.text.toString() == "F")
            points++
        if(binding.thirdEv.text.toString() == "F")
            points++
        if(binding.fourthEv.text.toString() == "F")
            points++

    }

    private fun onButtonClick(){
        checkAnswers()
        showToastWithPoints()
        resetPoints()
    }

}