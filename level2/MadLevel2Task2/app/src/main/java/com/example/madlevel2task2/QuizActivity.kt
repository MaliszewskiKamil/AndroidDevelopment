package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.adapters.QuestionAdapter
import com.example.madlevel2task2.data.Question
import com.example.madlevel2task2.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {

        binding.questionsRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.questionsRv.adapter = questionAdapter

        for(i in Question.QUESTIONS.indices){
            questions.add(Question(Question.QUESTIONS[i], Question.ANSWERS[i]))
        }
    }



    private fun toastOnWrongAnswer(){
        Toast.makeText(this, "This answer is wrong, try again", Toast.LENGTH_LONG).show()
    }
}