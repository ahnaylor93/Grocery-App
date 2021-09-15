package com.example.groceryapp.holder

import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageLoader
import com.example.groceryapp.R
import com.example.groceryapp.data.Category
import com.example.groceryapp.data.Product
import com.example.groceryapp.data.SubCategory
import com.example.groceryapp.databinding.FragmentHolderProductBinding
import com.example.groceryapp.databinding.FragmentHolderSubcategoryBinding

class ProductHolder(val binding: FragmentHolderProductBinding): RecyclerView.ViewHolder(binding.root) {

    val tvCategory = binding.tvProductName
    val tvDesc = binding.tvProductDesc
    val tvPrice = binding.tvPrice
    val ivImage = binding.ivProductImage

    fun bind(product: Product, imageLoader: ImageLoader){
        tvCategory.text = product.productName
        tvPrice.text = "$${product.price}"
        tvDesc.text = product.description
        imageLoader.get(
            "https://rjtmobile.com/grocery/images/${product.image}",
            ImageLoader.getImageListener(binding.ivProductImage,
                R.drawable.ic_default, R.drawable.ic_error)
        )
        binding.ivProductImage.setImageUrl(
            "https://rjtmobile.com/grocery/images/${product.image}",
            imageLoader)
    }
}