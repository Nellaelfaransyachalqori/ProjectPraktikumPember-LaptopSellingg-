package com.example.laptopsellingg.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.laptopsellingg.room.Order
import com.example.laptopsellingg.room.SellingDatabase
import kotlinx.coroutines.launch

class OrderViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = SellingDatabase.getInstance(application)?.orderDao()

    fun getAllOrder(id: Int): LiveData<List<Order>>? {
        return dao?.getAllOrder(id)
    }

    fun deleteOrder(order: Order) {
        viewModelScope.launch {
            dao?.deleteOrder(order)
        }
    }
}
