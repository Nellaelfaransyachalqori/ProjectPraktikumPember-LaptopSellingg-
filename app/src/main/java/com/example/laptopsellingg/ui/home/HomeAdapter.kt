package com.example.laptopsellingg.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.laptopsellingg.MainActivity
import com.example.laptopsellingg.R
import com.example.laptopsellingg.databinding.ProductItemBinding
import com.example.laptopsellingg.room.Item
import java.text.DecimalFormat

class HomeAdapter(val list: List<Item>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val item = list[position]

        with(holder) {
            Glide.with(itemView).load(item.productImage).placeholder(R.drawable.place_holder).into(binding.instrumentImg)
            binding.instrumentName.text = item.productName
            binding.instrumentPrice.text = convertIdr(item.productPrice.toDouble())

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MainActivity::class.java)
                intent.putExtra("item", item)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root)

    private fun convertIdr(value: Double?): String {
        val formatter = DecimalFormat("#,###")
        val formattedString = formatter.format(value)
        return "Rp. ${formattedString.replace(",", ".")}"
    }

}