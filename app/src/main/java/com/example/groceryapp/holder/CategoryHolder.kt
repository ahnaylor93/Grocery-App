package com.example.groceryapp.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageLoader
import com.example.groceryapp.R
import com.example.groceryapp.data.Category
import com.example.groceryapp.databinding.HolderCategoryBinding

class CategoryHolder(val binding: HolderCategoryBinding): RecyclerView.ViewHolder(binding.root) {

    val tvCategory = binding.tvCategory
    val tvDesc = binding.tvCategoryDesc
    val ivImage = binding.ivCategoryImage

    fun bind(category: Category, imageLoader: ImageLoader){
        tvCategory.text = category.catName
        tvDesc.text = category.catDescription
        imageLoader.get(
            "https://rjtmobile.com/grocery/images/${category.catImage}",
            ImageLoader.getImageListener(binding.ivCategoryImage,
            R.drawable.ic_default, R.drawable.ic_error)
        )
        binding.ivCategoryImage.setImageUrl(
            "https://rjtmobile.com/grocery/images/${category.catImage}",
        imageLoader)
    }
}