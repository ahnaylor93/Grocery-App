package com.example.groceryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.CartItem
import com.example.groceryapp.databinding.HolderCartItemBinding
import com.example.groceryapp.holder.CartItemHolder

class CartItemAdapter(val cartItemList: ArrayList<CartItem>): RecyclerView.Adapter<CartItemHolder>() {

    lateinit var addClickListener: (CartItem, Int) -> Unit
    lateinit var binding: HolderCartItemBinding

    fun setOnAddClickListener(listener: (CartItem, Int) -> Unit){
        addClickListener = listener
    }

    lateinit var subtractClickListener: (CartItem, Int) -> Unit

    fun setOnSubtractClickListener(listener: (CartItem, Int) -> Unit){
        subtractClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = HolderCartItemBinding.inflate(layoutInflater, parent, false)
        return CartItemHolder(binding)
    }

    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {
        cartItemList?.let {
            holder.bind(it[position])


            if (this::addClickListener.isInitialized) {
                holder.itemView.setOnClickListener {
                    addClickListener(cartItemList[position], position)
                }
            }

            if (this::subtractClickListener.isInitialized) {
                holder.itemView.setOnClickListener {
                    subtractClickListener(cartItemList[position], position)
                }
            }
        }
    }

    override fun getItemCount() = cartItemList.size

}