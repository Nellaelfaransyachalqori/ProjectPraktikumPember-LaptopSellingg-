package com.example.laptopsellingg.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {
    @Insert
    suspend fun addItem(item: Item)

    @Query("SELECT * FROM selling_item")
    fun getAllItem(): LiveData<List<Item>>
}