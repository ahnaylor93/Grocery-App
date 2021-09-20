package com.example.groceryapp.holder

import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageLoader
import com.example.groceryapp.R
import com.example.groceryapp.data.Category
import com.example.groceryapp.data.SubCategory
import com.example.groceryapp.databinding.HolderSubcategoryBinding

class SubCategoryHolder(val binding: HolderSubcategoryBinding): RecyclerView.ViewHolder(binding.root) {

    val tvCategory = binding.tvSubCategory
    val tvDesc = binding.tvSubCategoryDesc
    val ivImage = binding.ivSubCategoryImage

    fun bind(subcategory: SubCategory, imageLoader: ImageLoader){
        tvCategory.text = subcategory.subName
        tvDesc.text = subcategory.subDescription
        imageLoader.get(
            "https://rjtmobile.com/grocery/images/${subcategory.subImage}",
            ImageLoader.getImageListener(binding.ivSubCategoryImage,
                R.drawable.ic_default, R.drawable.ic_error)
        )
        binding.ivSubCategoryImage.setImageUrl(
            "https://rjtmobile.com/grocery/images/${subcategory.subImage}",
            imageLoader)
    }
}