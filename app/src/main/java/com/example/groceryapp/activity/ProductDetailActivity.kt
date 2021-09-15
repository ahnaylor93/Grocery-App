package com.example.groceryapp.activity

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LruCache
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonObjectRequest
import com.example.groceryapp.R
import com.example.groceryapp.data.Product
import com.example.groceryapp.databinding.ActivityProductDetailBinding
import org.json.JSONObject
import android.graphics.BitmapFactory
import java.net.URL


class ProductDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDetailBinding
    lateinit var product: Product
    lateinit var imageLoader: ImageLoader

    companion object{
        var productId:String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        product = intent.extras?.get("product") as Product

        binding.tvProductName.text = product.productName
        binding.tvPrice.text = "$${product.price}"
        binding.tvProductDesc.text = product.description
    }
}