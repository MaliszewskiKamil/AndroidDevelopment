package com.example.level4task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.level4task2.R
import com.example.level4task2.adapter.GameAdapter
import com.example.level4task2.model.Game
import com.example.level4task2.repository.GameRepository
import com.example.level4task2.tools.Converters
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    private lateinit var converters: Converters
    private lateinit var gameRepository: GameRepository
    private val games = arrayListOf<Game>()
    private val mainScope = CoroutineScope(Dispatchers.Main)



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())
        initViews()
    }

    private fun initViews(){
        ivRock.setOnClickListener { onRockClick() }
        ivPaper.setOnClickListener { onPaperClick() }
        ivScissors.setOnClickListener { onScissorsClick() }
    }


    private fun onRockClick(){
        val player = R.drawable.rock
        ivPlayerImage.setImageResource(R.drawable.rock)
        val computer = computerChoose()
        val results = checkResults(R.drawable.rock, computerChoose())
        updateUi(results)
        val dateSql = Date(System.currentTimeMillis())
        addGame(results, player, computer, dateSql)


    }
    private fun onPaperClick(){
        ivPlayerImage.setImageResource(R.drawable.paper)
        val results = checkResults(R.drawable.paper, computerChoose())
        updateUi(results)
    }
    private fun onScissorsClick(){
        ivPlayerImage.setImageResource(R.drawable.scissors)
        val results = checkResults(R.drawable.scissors, computerChoose())
        updateUi(results)

    }

    private fun checkResults(player: Int, computer: Int) : String{
        return if((player == R.drawable.paper && computer == R.drawable.rock)
            || (player == R.drawable.scissors) && computer == R.drawable.paper ||
            (player == R.drawable.rock && computer == R.drawable.scissors)){
            "You win!"
        } else if ((player == R.drawable.rock && computer == R.drawable.paper)
            || (player == R.drawable.paper) && computer == R.drawable.scissors ||
            (player == R.drawable.scissors && computer == R.drawable.rock)) {
            "Computer wins!"
        } else {
            "Draw"
        }

    }

    private fun updateUi(result: String){
        tvWhoWins.text = result
    }

    private fun computerChoose(): Int{
        when((0..2).random()) {
            0 -> {
                changeComputerIcon(R.drawable.rock)
                return R.drawable.rock
            }
            1 -> {
                changeComputerIcon(R.drawable.paper)
                return R.drawable.paper
            }
            2 -> {
                changeComputerIcon(R.drawable.scissors)
                return R.drawable.scissors
            }
        }
        return 1
    }

    private fun changeComputerIcon(imageId: Int){
        ivComputerImage.setImageResource(imageId)
    }

    private fun addGame(result: String, player: Int, computer: Int, date: Date){
        mainScope.launch {
            val game = Game(
                result = result,
                playerChoice = player,
                computerChoice = computer,
                gameDate =  date)
            withContext(Dispatchers.IO){
                gameRepository.insertGame(game)
            }
        }
    }


//    private fun getDate(): String{
//        var strDate = "2020-07-10 04:00:00+0000"
//        var result: Date?
//        var dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ")
//        dateFormatter.timeZone = TimeZone.getTimeZone("Asia/Jerusalem")
//        result = dateFormatter.parse(strDate)
//        return result.toString()
//    }


}