package com.example.madlevel3example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminderTable")
data class Reminder(
        @ColumnInfo
        var reminderText: String,

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="id")
        var id: Long? = null

)