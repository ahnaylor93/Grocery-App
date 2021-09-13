package com.example.groceryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.groceryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
    }
}