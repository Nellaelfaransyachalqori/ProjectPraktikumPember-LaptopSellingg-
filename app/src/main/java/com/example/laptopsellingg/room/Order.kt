package com.example.laptopsellingg.room

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.Companion.INTEGER
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order(
    @PrimaryKey
    @ColumnInfo(name = "id", typeAffinity = INTEGER)
    val id: Long,
    @ColumnInfo(name = "id_user", typeAffinity = INTEGER)
    val idUser: Int,
    @ColumnInfo(name = "product_name", typeAffinity = ColumnInfo.TEXT)
    val productName: String,
    @ColumnInfo(name = "product_image", typeAffinity = ColumnInfo.TEXT)
    val productImage: String,
    @ColumnInfo(name = "product_price", typeAffinity = INTEGER)
    val productPrice: Int,
)