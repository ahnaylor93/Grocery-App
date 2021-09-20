package com.example.groceryapp.activity

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LruCache
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.groceryapp.R
import com.example.groceryapp.adapter.CategoryAdapter
import com.example.groceryapp.data.Category
import com.example.groceryapp.databinding.ActivityHomeBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var requestQueue: RequestQueue
    lateinit var imageLoader: ImageLoader
    lateinit var adapter: CategoryAdapter
    var categories: ArrayList<Category> = ArrayList<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue((baseContext))
        imageLoader = ImageLoader(requestQueue, cache)
        binding.rvCategories.layoutManager = LinearLayoutManager(baseContext)

        loadCategories()
    }

    val cache = object: ImageLoader.ImageCache {
        val lruCache: LruCache<String, Bitmap> = LruCache(100)

        override fun getBitmap(url: String?): Bitmap? {
            return lruCache[url]
        }

        override fun putBitmap(url: String?, bitmap: Bitmap?) {
            url?.let{
                lruCache.put(it, bitmap)
            }
        }
    }

    private fun loadCategories() {
        val request = object: JsonObjectRequest(
            "https://grocery-second-app.herokuapp.com/api/category",
            Response.Listener<JSONObject> { response ->

                val categoriesJSON = response.getJSONArray("data").toString()
                val gson = Gson()
                val type = object: TypeToken<ArrayList<Category>>() {}.type

                categories = gson.fromJson(categoriesJSON, type)
                adapter = CategoryAdapter(categories, imageLoader)

                adapter.setOnCategoryClickListener{ category, position ->
                    val subCategoryIntent = Intent(baseContext, SubCategoryActivity::class.java)
                    subCategoryIntent.putExtra("catId", category.catId)
                    startActivity(subCategoryIntent)
                }
                binding.rvCategories.adapter = adapter
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                Toast.makeText(baseContext, "Error: ${error.toString()}", Toast.LENGTH_LONG).show()
            }
        ){}

        requestQueue.add(request)
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