package com.example.groceryapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        binding.btnPrint.setOnClickListener{
            Toast.makeText(baseContext, "TEST", Toast.LENGTH_LONG).show()
            Log.d("DATABASE","$cartItems")
        }

        binding.btnCheckout.setOnClickListener{
            checkout()
        }
    }

    private fun checkout() {

    }

    private fun populateList() {
       adapter = CartItemAdapter(cartItems, cartDao)

        binding.rvCart.adapter = adapter
    }

}