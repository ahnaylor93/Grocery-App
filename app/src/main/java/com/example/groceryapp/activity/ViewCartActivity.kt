package com.example.groceryapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.adapter.CartItemAdapter
import com.example.groceryapp.data.CartItem
import com.example.groceryapp.databinding.ActivityViewCartBinding
import com.example.groceryapp.sql.CartDao
import com.squareup.picasso.Picasso

class ViewCartActivity : AppCompatActivity() {

    lateinit var binding: ActivityViewCartBinding
    lateinit var adapter: CartItemAdapter
    lateinit var cartDao: CartDao
    lateinit var cartItems: ArrayList<CartItem>
    lateinit var picasso: Picasso


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        picasso = Picasso.get()
        cartDao = CartDao(baseContext)
        cartItems = cartDao.getItems()

        binding.rvCart.layoutManager = LinearLayoutManager(baseContext)

        populateList()
    }

    private fun populateList() {
       adapter = CartItemAdapter(cartItems)

        adapter.setOnAddClickListener { cartItem, position ->
            when (cartItem.quantity) {
                0 -> {
                    cartItem.quantity++
                    adapter.binding.btnSubtract.text = "@string/btn_minus"
                    adapter.binding.tvQuantity.text = cartItem.quantity.toString()
                }
                else -> {
                    cartItem.quantity++
                    adapter.binding.tvQuantity.text = cartItem.quantity.toString()
                }
            }
            adapter.notifyItemChanged(position)
        }
        adapter.setOnSubtractClickListener { cartItem, position ->
            when(cartItem.quantity){
                0 -> { removeItem(position) }
                1 -> { cartItem.quantity--
                    adapter.binding.btnSubtract.text = "@string/btn_remove"
                    adapter.binding.tvQuantity.text = cartItem.quantity.toString()
                    adapter.notifyItemChanged(position)
                }
                else -> {cartItem.quantity--
                adapter.binding.tvQuantity.text = cartItem.quantity.toString()
                    adapter.notifyDataSetChanged()
                }
            }
        }
        binding.rvCart.adapter = adapter
    }

    private fun removeItem(position: Int) {
        Toast.makeText(baseContext, "Remove item:${cartItems[position]}\n At position: $position",
        Toast.LENGTH_LONG).show()
    }
}