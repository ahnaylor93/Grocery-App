package com.example.groceryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.R
import com.example.groceryapp.data.CartItem
import com.example.groceryapp.databinding.HolderCartItemBinding
import com.example.groceryapp.holder.CartItemHolder
import com.example.groceryapp.sql.CartDao

class CartItemAdapter(val cartItemList: ArrayList<CartItem>,val cartDao: CartDao): RecyclerView.Adapter<CartItemHolder>() {

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
            val cartItem = it[position]
            holder.bind(it[position])
            binding.btnAdd.setOnClickListener{
                //Toast.makeText(, "btnPlus: $cartItem\nPosition: $position", Toast.LENGTH_LONG).show()
                when (cartItem.quantity) {
                    0 -> {
                        cartItem.quantity++
                        holder.binding.btnSubtract.text = "-"
                        cartDao.updateItem(cartItem)
                        holder.binding.tvQuantity.text = cartItem.quantity.toString()
                    }
                    else -> {
                        cartItem.quantity++
                        cartDao.updateItem(cartItem)
                        holder.binding.tvQuantity.text = cartItem.quantity.toString()
                    }
                }
                notifyDataSetChanged()
            }
            binding.btnSubtract.setOnClickListener{
                when(cartItem.quantity){
                    0 -> {
                        cartDao.deleteItem(cartItem.itemId)
                        holder.binding.btnSubtract.text = "-"
                        cartItemList.remove(cartItem)
                    }
                    1 -> { cartItem.quantity--
                        cartDao.updateItem(cartItem)
                        holder.binding.btnSubtract.text = "Remove"
                        holder.binding.tvQuantity.text = cartItem.quantity.toString()
                    }
                    else -> {cartItem.quantity--
                        cartDao.updateItem(cartItem)
                        holder.binding.tvQuantity.text = cartItem.quantity.toString()
                    }
                }
                notifyDataSetChanged()
            }

        /*
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
            */
        }
    }

    override fun getItemCount() = cartItemList.size

}