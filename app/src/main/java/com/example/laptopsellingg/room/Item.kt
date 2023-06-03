package com.example.laptopsellingg.room

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.Companion.INTEGER
import androidx.room.ColumnInfo.Companion.TEXT
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "selling_item")
data class Item(
    @PrimaryKey
    @ColumnInfo(name = "no_product", typeAffinity = INTEGER)
    val noProduct: Int,
    @ColumnInfo(name = "product_name", typeAffinity = TEXT)
    val productName: String,
    @ColumnInfo(name = "product_image", typeAffinity = TEXT)
    val productImage: String,
    @ColumnInfo(name = "product_price", typeAffinity = INTEGER)
    val productPrice: Int,
    @ColumnInfo(name = "processor", typeAffinity = TEXT)
    val processor: String,
    @ColumnInfo(name = "memory", typeAffinity = TEXT)
    val memory: String,
    @ColumnInfo(name = "storage", typeAffinity = TEXT)
    val storage: String,
    @ColumnInfo(name = "graphics", typeAffinity = TEXT)
    val graphics: String,
    @ColumnInfo(name = "os", typeAffinity = TEXT)
    val os: String,
    @ColumnInfo(name = "description", typeAffinity = TEXT)
    val description: String
): Serializable