package com.example.laptopsellingg

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.laptopsellingg.databinding.ActivityMainBinding
import com.example.laptopsellingg.room.Item
import com.example.laptopsellingg.room.Order
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("item", Item::class.java)
        } else {
            intent.getSerializableExtra("item") as Item
        }

        val preferences = Preferences(this)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        if (item != null) {
            Glide.with(this).load(item.productImage).placeholder(R.drawable.place_holder).into(binding.productImg)
            binding.productNameTxt.text = item.productName
            binding.priceTxt.text = convertIdr(item.productPrice.toDouble())
            binding.processorTxt.text = item.processor
            binding.memoryTxt.text = item.memory
            binding.operatingTxt.text = item.os
            binding.graphicsTxt.text = item.graphics
            binding.storageTxt.text = item.storage
            binding.descTxt.text = item.description
        }

        binding.orderButton.setOnClickListener {
            val id = System.currentTimeMillis()
            if (item != null) {
                val order = Order(id, preferences.getSession(), item.productName, item.productImage, item.productPrice)
                viewModel.addOrder(order)
                Toast.makeText(this, "Pesanan Laptopmu Ditambahkan", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun convertIdr(value: Double?): String {
        val formatter = DecimalFormat("#,###")
        val formattedString = formatter.format(value)
        return "Rp. ${formattedString.replace(",", ".")}"
    }
}

