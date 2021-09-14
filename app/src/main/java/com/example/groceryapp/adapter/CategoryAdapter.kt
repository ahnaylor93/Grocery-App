package com.example.groceryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageLoader
import com.example.groceryapp.data.Category
import com.example.groceryapp.databinding.FragmentHolderCategoryBinding
import com.example.groceryapp.holder.CategoryHolder

class CategoryAdapter(val categoryList: ArrayList<Category>, val imgLoader: ImageLoader): RecyclerView.Adapter<CategoryHolder>() {

    lateinit var categoryClickListener: (Category, Int) -> Unit

    fun setOnCategoryClickListener(listener: (Category, Int) -> Unit){
        categoryClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentHolderCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoryList[position],imgLoader)

        if(this::categoryClickListener.isInitialized){
            holder.itemView.setOnClickListener{
                categoryClickListener(categoryList[position], position)
            }
        }
    }

    override fun getItemCount() = categoryList.size
}