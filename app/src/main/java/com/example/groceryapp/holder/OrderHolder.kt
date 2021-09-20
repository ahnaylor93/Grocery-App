package com.example.groceryapp.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.Order
import com.example.groceryapp.databinding.HolderOrderBinding

class OrderHolder(val binding: HolderOrderBinding): RecyclerView.ViewHolder(binding.root) {

    val tvOrderId = binding.tvOrderID
    val tvDate = binding.tvDate
    val tvItems = binding.tvItems
    val tvTotal = binding.tvTotal

    fun bind(order: Order){
        tvOrderId.text = order._id
        tvDate.text = order.date
        tvItems.text = "Number of Items: ${order.items}"
        tvTotal.text = "$${order.total}"
    }
}