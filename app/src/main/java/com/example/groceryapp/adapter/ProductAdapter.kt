package com.example.groceryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageLoader
import com.example.groceryapp.data.Product
import com.example.groceryapp.databinding.HolderProductBinding
import com.example.groceryapp.holder.ProductHolder

class ProductAdapter(val productList: ArrayList<Product>, val imgLoader: ImageLoader): RecyclerView.Adapter<ProductHolder>() {

    lateinit var productClickListener: (Product, Int) -> Unit

    fun setOnProductClickListener(listener: (Product, Int) -> Unit){
        productClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderProductBinding.inflate(layoutInflater, parent, false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productList[position],imgLoader)

        if(this::productClickListener.isInitialized){
            holder.itemView.setOnClickListener{
                productClickListener(productList[position], position)
            }
        }
    }

    override fun getItemCount() = productList.size
}