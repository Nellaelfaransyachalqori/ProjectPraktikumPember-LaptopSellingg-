package com.example.laptopsellingg

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.laptopsellingg.room.Order
import com.example.laptopsellingg.room.SellingDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val orderDao = SellingDatabase.getInstance(application)?.orderDao()

    fun addOrder(order: Order) {
        viewModelScope.launch {
            orderDao?.addOrder(order)
        }
    }
}
