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
        when (points) {
            4 -> {
                Toast.makeText(this, getString(R.string.all_answer_correct, points), Toast.LENGTH_LONG).show()
            }
            0 -> {
                Toast.makeText(this, getString(R.string.all_answers_wrong, points), Toast.LENGTH_LONG).show()
            }
            else -> {
                Toast.makeText(this, getString(R.string.correct_answers, points), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkAnswers(){
        if(binding.firstEv.text.toString() == "T" || binding.firstEv.text.toString() == "t")
            points++
        if(binding.secondEv.text.toString() == "F" || binding.secondEv.text.toString() == "f")
            points++
        if(binding.thirdEv.text.toString() == "F" || binding.thirdEv.text.toString() == "f")
            points++
        if(binding.fourthEv.text.toString() == "F" || binding.fourthEv.text.toString() == "f")
            points++

    }

    private fun onButtonClick(){
        checkAnswers()
        showToastWithPoints()
        resetPoints()
    }

}