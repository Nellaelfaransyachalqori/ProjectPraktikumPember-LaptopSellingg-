package com.example.laptopsellingg.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.laptopsellingg.room.Item
import com.example.laptopsellingg.room.SellingDatabase

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = SellingDatabase.getInstance(application)?.itemDao()

    fun getAllItem(): LiveData<List<Item>>? {
        return dao?.getAllItem()
    }
}
