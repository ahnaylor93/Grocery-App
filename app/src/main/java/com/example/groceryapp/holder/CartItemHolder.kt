package com.example.groceryapp.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.CartItem
import com.example.groceryapp.databinding.HolderCartItemBinding
import com.squareup.picasso.Picasso

class CartItemHolder(val binding: HolderCartItemBinding): RecyclerView.ViewHolder(binding.root) {

    val tvName = binding.tvProductName
    val tvPrice = binding.tvPrice
    val tvQuantity = binding.tvQuantity
    val ivImage = binding.ivProductImage
    val picasso = Picasso.get()

    fun bind(cartItem: CartItem){
        tvName.text = cartItem.productName
        tvPrice.text = "$${cartItem.price * cartItem.quantity}"
        tvQuantity.text = cartItem.quantity.toString()
        picasso.load("https://rjtmobile.com/grocery/images/${cartItem.image}").into(ivImage)
    }
}