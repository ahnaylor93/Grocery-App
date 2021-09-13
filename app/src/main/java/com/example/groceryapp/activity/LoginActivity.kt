package com.example.groceryapp.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.groceryapp.databinding.ActivityLoginBinding
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    lateinit var binding:ActivityLoginBinding
    lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(baseContext)
        setupEvents()
    }

    private fun setupEvents(){
        binding.btnExit.setOnClickListener{

        }

        binding.btnSignIn.setOnClickListener{
            signIn()
        }

        binding.btnRegister.setOnClickListener{
            startActivity(Intent(baseContext, RegisterActivity::class.java))
        }

        binding.btnForgotPassword.setOnClickListener{
            //goto Forgot Password Activity
        }
    }

    private fun signIn() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        val loginRequestData = JSONObject()
        loginRequestData.put("email", email)
        loginRequestData.put("password", password)

        val request = JsonObjectRequest(
            Request.Method.GET,
            "https://grocery-second-app.herokuapp.com/api/auth/login",
            loginRequestData,
            Response.Listener<JSONObject>{ response ->
                if(response.getBoolean("error")){
                    Toast.makeText(baseContext, "Invalid Login Information, please try again",
                        Toast.LENGTH_LONG).show()
                }else{
                    val sharedPref = getSharedPreferences("app_settings", MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPref.edit()
                    editor.putString("firstname", response.getString("firstname"))
                    startActivity(Intent(baseContext, HomeActivity::class.java))
                }

            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                Toast.makeText(baseContext, "Error is: $error", Toast.LENGTH_LONG).show()
            }
        )
        requestQueue.add(request)
    }
}