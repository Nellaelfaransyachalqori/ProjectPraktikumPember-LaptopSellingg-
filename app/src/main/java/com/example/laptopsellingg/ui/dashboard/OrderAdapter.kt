package com.example.laptopsellingg.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.laptopsellingg.R
import com.example.laptopsellingg.databinding.OrderItemBinding
import com.example.laptopsellingg.room.Order
import java.text.DecimalFormat

class OrderAdapter(private val list: List<Order>, private val deleteClick: DeleteClick) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) {
        val order = list[position]

        with(holder) {
            Glide.with(itemView).load(order.productImage).placeholder(R.drawable.place_holder).into(binding.productOImg)
            binding.productOName.text = order.productName
            binding.priceOTxt.text = convertIdr(order.productPrice.toDouble())

            binding.deleteCard.setOnClickListener {
                deleteClick.onClick(order)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: OrderItemBinding): RecyclerView.ViewHolder(binding.root)

    private fun convertIdr(value: Double?): String {
        val formatter = DecimalFormat("#,###")
        val formattedString = formatter.format(value)
        return "Rp. ${formattedString.replace(",", ".")}"
    }

}