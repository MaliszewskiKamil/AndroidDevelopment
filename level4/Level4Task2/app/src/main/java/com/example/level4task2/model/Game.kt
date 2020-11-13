package com.example.level4task2.model

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.level4task2.R
import java.sql.Date
import java.util.*

@Entity(tableName = "gameTable")
data class Game (

    @ColumnInfo
    var result: String,

    @ColumnInfo
    var playerChoice: Int,

    @ColumnInfo
    var computerChoice: Int,

    //@ColumnInfo
    //var gameDate: String,


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Long? = null
)
