package com.example.groceryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.Order
import com.example.groceryapp.databinding.HolderOrderBinding
import com.example.groceryapp.holder.OrderHolder
import com.example.groceryapp.holder.ProductHolder

class OrderAdapter(val orderList: ArrayList<Order>): RecyclerView.Adapter<OrderHolder>() {

    lateinit var binding: HolderOrderBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderOrderBinding.inflate(layoutInflater, parent, false)
        return OrderHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.bind(orderList[position])
    }

    override fun getItemCount() = orderList.count()
}