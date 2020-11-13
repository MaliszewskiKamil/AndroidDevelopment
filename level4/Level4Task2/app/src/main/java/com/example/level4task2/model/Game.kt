package com.example.level4task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.util.*

@Entity(tableName = "gameTable")
data class Game (

    @ColumnInfo
    var playerChoice: Int,

    @ColumnInfo
    var computerChoice: Int,

    @ColumnInfo
    var gameDate: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Long? = null

)