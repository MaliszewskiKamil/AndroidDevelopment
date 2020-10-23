package com.example.madlevel3task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_rating.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
const val ARG_GAME_NAME = "arg_game_name"
const val ARG_GAME_RATING = "arg_game_rating"
class RatingFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_summary.setOnClickListener{
            navigateToSummary()
        }

        showRandomAssessableGame()


    }

    private fun showRandomAssessableGame() {
        val randomGame = listOf("Red Dead Redemption 2", "Rocket League", "Shadow of the Tombraider",
            "Lineage 2", "League of Legends", "Counter String 1.6").random()

        tv_rating_sub.text = randomGame
    }

    private fun navigateToSummary() {

        val args = Bundle()
        args.putFloat(ARG_GAME_RATING, rating_bar.rating)
        args.putString(ARG_GAME_NAME, tv_rating_sub.text.toString())

        findNavController().navigate(
            R.id.action_ratingFragment_to_summaryFragment
        )
    }

}