package com.example.laptopsellingg.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun register(user: User)

    @Query("SELECT EXISTS (SELECT * FROM user WHERE email =:email)")
    suspend fun checkEmailIsExist(email: String): Boolean

    @Query("SELECT * FROM user WHERE email =:email")
    suspend fun login(email: String): User
}