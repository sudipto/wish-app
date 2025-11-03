package com.example.mywishlistapp

import android.content.Context
import androidx.room.Room
import com.example.mywishlistapp.data.WishDatabase
import com.example.mywishlistapp.data.WishRepository

object Graph {
    lateinit var database: WishDatabase

    val repository by lazy {
        WishRepository(wishDao = database.WishDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(
            context,
            WishDatabase::class.java,
            "wishlist.db"
        ).build()
    }
}