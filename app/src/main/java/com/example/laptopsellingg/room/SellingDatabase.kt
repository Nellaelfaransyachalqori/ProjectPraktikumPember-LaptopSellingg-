package com.example.laptopsellingg.room

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.laptopsellingg.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

@Database(entities = [Item::class, Order::class, User::class], version = 1, exportSchema = false)
abstract class SellingDatabase(_openCallback: RoomOpenHelper) : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun orderDao(): OrderDao
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: SellingDatabase? = null

        fun getInstance(context: Context): SellingDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SellingDatabase::class.java,
                        "selling.db"
                    )
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                loadDataFromJson(context)
                            }
                        })
                        .build()
                }
                return INSTANCE as SellingDatabase
            }
        }

        private fun loadDataFromJson(context: Context) {
            val inputStream = context.resources.openRawResource(R.raw.item_json)

            BufferedReader(inputStream.reader()).use {
                val jsonArray = JSONArray(it.readText())

                try {
                    for (i in 0 until jsonArray.length()) {
                        val get = jsonArray.getJSONObject(i)

                        val item = Item(
                            get.getInt("no_product"),
                            get.getString("product_name"),
                            get.getString("product_image"),
                            get.getInt("product_price"),
                            get.getString("processor"),
                            get.getString("memory"),
                            get.getString("storage"),
                            get.getString("graphics"),
                            get.getString("os"),
                            get.getString("description")
                        )
                        CoroutineScope(Dispatchers.IO).launch {
                            getInstance(context).itemDao().addItem(item)
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
    }
    }
}