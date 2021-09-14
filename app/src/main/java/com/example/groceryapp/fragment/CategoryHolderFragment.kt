package com.example.groceryapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.groceryapp.databinding.FragmentHolderCategoryBinding

class CategoryHolderFragment: Fragment() {
    lateinit var binding: FragmentHolderCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHolderCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}