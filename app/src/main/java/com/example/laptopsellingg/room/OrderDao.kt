package com.example.laptopsellingg.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {
    @Insert
    suspend fun addOrder(order: Order)

    @Query("SELECT * FROM `order` WHERE id_user =:id")
    fun getAllOrder(id: Int): LiveData<List<Order>>

    @Delete
    suspend fun deleteOrder(order: Order)
}