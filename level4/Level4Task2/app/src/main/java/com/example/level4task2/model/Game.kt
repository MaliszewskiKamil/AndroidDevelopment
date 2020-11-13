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
    @DrawableRes var playerChoice: Int,

    @ColumnInfo
    @DrawableRes var computerChoice: Int,

    @ColumnInfo
    var gameDate: Long,


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Long? = null
)
