package com.example.groceryapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.groceryapp.databinding.FragmentHolderSubcategoryBinding

class SubCategoryFragment: Fragment() {
    lateinit var binding: FragmentHolderSubcategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHolderSubcategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}