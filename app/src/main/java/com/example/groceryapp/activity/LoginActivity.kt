package com.example.groceryapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.groceryapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEvents()
    }

    private fun setupEvents(){
        binding.btnSignIn.setOnClickListener{

        }

        binding.btnRegister.setOnClickListener{
            //goto Register Activity
        }

        binding.btnForgotPassword.setOnClickListener{
            //goto Forgot Password Activity
        }
    }
}