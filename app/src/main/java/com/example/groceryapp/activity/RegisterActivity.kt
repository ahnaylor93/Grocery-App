package com.example.groceryapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.groceryapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEvents()
    }

    private fun setupEvents() {
        binding.btnExit.setOnClickListener{
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }

        binding.btnLogin.setOnClickListener{
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener{
            registerUser()
        }
    }

    private fun registerUser() {

    }
}