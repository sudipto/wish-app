package com.example.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title: String = "",
    @ColumnInfo(name = "wish-desc")
    val description: String = ""
)

object DummyWish{
    val wishList = listOf(
        Wish(title = "Apple",
            description = "This is a fruit"),
        Wish(title = "Watch",
            description = "A smart watch"),
        Wish(title = "Potato",
            description = "A root vegetable"),
        Wish(title = "Sci-fi book",
            description = "A science fiction book")
    )
}