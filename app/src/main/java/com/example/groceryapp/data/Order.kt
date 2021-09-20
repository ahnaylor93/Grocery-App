package com.example.groceryapp.data

data class Order(
    val _id: String,
    val orderStatus: String,
    val total: Double,
    val orderAmount: Double,
    val items: Int,
    val date: String
)
