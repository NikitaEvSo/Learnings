package com.example.learnings.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.temporal.TemporalAmount

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Room will replace 0 with a real ID
    val title: String,
    val description: String
)

@Entity(tableName = "user_cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = false)
    val productId: Int,
    val amount: Int
)