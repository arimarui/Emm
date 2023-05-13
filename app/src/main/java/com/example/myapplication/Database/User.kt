package com.example.myapplication.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey()
    var username : String,
    @ColumnInfo
    var password : String,
)
