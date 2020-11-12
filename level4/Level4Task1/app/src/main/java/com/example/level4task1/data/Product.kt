package com.example.level4task1.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productTable")
data class Product (
    @ColumnInfo
    var productText: String,

    @ColumnInfo
    var productQuantity: Integer,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Long? = null


)