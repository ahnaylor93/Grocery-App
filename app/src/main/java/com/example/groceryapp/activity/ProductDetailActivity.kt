package com.example.groceryapp.activity

import android.content.Intent
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
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.groceryapp.data.CartItem
import com.example.groceryapp.sql.CartDao
import com.squareup.picasso.Picasso
import java.net.URL


class ProductDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDetailBinding
    lateinit var product: Product
    lateinit var picasso: Picasso

    lateinit var cartDao: CartDao

    companion object{
        var productId:String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartDao = CartDao(baseContext)

        product = intent.extras?.get("product") as Product

        binding.tvProductName.text = product.productName
        binding.tvPrice.text = "$${product.price}"
        binding.tvProductDesc.text = product.description

        picasso = Picasso.get()

        picasso.load("https://rjtmobile.com/grocery/images/${product.image}").into(binding.ivProductImage)

        setupEvents()
    }

    private fun setupEvents() {
        binding.btnAddToCart.setOnClickListener{
            try {
                if(cartDao.hasItem(product)){
                    Toast.makeText(baseContext,"Item Found In Cart", Toast.LENGTH_LONG).show()
                }
                else{
                    val item = CartItem(0, product._id, product.productName, product.image,
                    1, product.price)
                    cartDao.addItem(item)
                    Toast.makeText(baseContext, "Item Added to Cart", Toast.LENGTH_LONG).show()
                }
            }catch(e: Exception){
                Toast.makeText(baseContext, "An Error has occurred: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnViewCart.setOnClickListener{
            startActivity(Intent(baseContext, ViewCartActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_home_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_cart -> {
                val cartIntent = Intent(baseContext, ViewCartActivity::class.java)
                startActivity(cartIntent)
            }
            R.id.action_orders -> {
                val ordersIntent = Intent(baseContext, ViewOrdersActivity::class.java)
                startActivity(ordersIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}