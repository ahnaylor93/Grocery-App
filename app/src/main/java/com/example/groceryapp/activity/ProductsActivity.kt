package com.example.groceryapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.groceryapp.databinding.ActivityProductsBinding

class ProductsActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}