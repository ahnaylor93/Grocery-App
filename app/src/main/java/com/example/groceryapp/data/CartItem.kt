package com.example.groceryapp.data

data class CartItem(
    val itemId: Long,
    val productId: String,
    val productName: String,
    val quantity: Int,
    val price: Double
)