package com.example.level4task2.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.level4task2.R
import com.example.level4task2.model.Game
import com.example.level4task2.tools.Converters
import kotlinx.android.synthetic.main.item_history.view.*

private lateinit var converters: Converters

class GameAdapter (private val games: List<Game>):
        RecyclerView.Adapter<GameAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun databind(game: Game){
            itemView.tvCardTitle.text = game.result
            itemView.tvCardDate.text = converters.fromTimestamp(game.gameDate).toString()
            itemView.ivCardComputerImage.setImageResource(game.computerChoice)
            itemView.ivCardPlayerImage.setImageResource(game.playerChoice)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }
}