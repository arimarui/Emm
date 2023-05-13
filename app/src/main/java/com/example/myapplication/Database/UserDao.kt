package com.example.myapplication.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insertUser(user : User)

    @Query("SELECT * FROM users")
    fun getAllRegisteredUsers() : User

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(
        username: String): User?

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun getUserByUsernameAndPassword(
        username: String,
        password: String): User?
}