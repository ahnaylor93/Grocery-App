package com.example.groceryapp.data

data class Order(
    val _id: String,
    val orderStatus: String,
    val deliveryCharges: Double,
    val totalAmount: Double,
    val discount: Double,
    val ourPrice: Double,
    val orderAmount: Double,
    val date: String
)
