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
        onSwipeLeft().attachToRecyclerView(binding.questionsRv)
        onSwipeRight().attachToRecyclerView(binding.questionsRv)

        binding.questionsRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.questionsRv.adapter = questionAdapter

        for(i in Question.QUESTIONS.indices){
            questions.add(Question(Question.QUESTIONS[i], Question.ANSWERS[i]))
        }
    }

    private fun onSwipeLeft(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if(!questions[position].questionAnswer){
                    questions.removeAt(position)
                    questionAdapter.notifyDataSetChanged()
                } else {
                    toastOnWrongAnswer()
                    questionAdapter.notifyDataSetChanged()
                }

            }

        }

        return ItemTouchHelper(callback)
    }

    private fun onSwipeRight(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if(questions[position].questionAnswer){
                    questions.removeAt(position)
                    questionAdapter.notifyDataSetChanged()
                } else {
                    toastOnWrongAnswer()
                    questionAdapter.notifyDataSetChanged()
                }

            }

        }

        return ItemTouchHelper(callback)
    }

    private fun toastOnWrongAnswer(){
        Toast.makeText(this, "This answer is wrong, try again", Toast.LENGTH_LONG).show()
    }
}