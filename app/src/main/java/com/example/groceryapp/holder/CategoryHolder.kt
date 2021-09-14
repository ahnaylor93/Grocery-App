package com.example.groceryapp.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageLoader
import com.example.groceryapp.data.Category
import com.example.groceryapp.databinding.FragmentHolderCategoryBinding

class CategoryHolder(val binding: FragmentHolderCategoryBinding): RecyclerView.ViewHolder(binding.root) {

    val tvCategory = binding.tvCategory
    val tvDesc = binding.tvCategoryDesc
    val ivImage = binding.ivCategoryImage

    fun bind(category: Category/*, imageLoader: ImageLoader*/){
        tvCategory.text = category.catName
        tvDesc.text = category.catDescription
    }
}