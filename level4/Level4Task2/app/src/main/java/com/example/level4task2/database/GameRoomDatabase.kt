package com.example.level4task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.level4task2.dao.GameDao
import com.example.level4task2.model.Game
import com.example.level4task2.tools.Converters

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameRoomDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAMES_DATABASE"

        @Volatile
        private var gameRoomDatabaseInstance: GameRoomDatabase? = null


        fun getDatabase(context: Context):
                GameRoomDatabase? {
            if(gameRoomDatabaseInstance == null){
                synchronized(GameRoomDatabase::class.java){
                    if(gameRoomDatabaseInstance == null) {
                        gameRoomDatabaseInstance = Room.databaseBuilder(context.applicationContext, GameRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return gameRoomDatabaseInstance
        }
    }
}