package com.example.groceryapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.groceryapp.databinding.ActivityRegisterBinding
import com.google.gson.JsonObject
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(baseContext)
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

    private fun validateInput(): Boolean{
        if(binding.etFirstName.text.toString().trim() == ""){
            Toast.makeText(baseContext, "Please enter a first name.", Toast.LENGTH_LONG).show()
            return false
        }
        if(binding.etMobile.text.toString().trim() == ""){
            Toast.makeText(baseContext, "Please enter a mobile number.", Toast.LENGTH_LONG).show()
            return false
        }
        if(binding.etEmail.text.toString().trim() == ""){
            Toast.makeText(baseContext, "Please enter an email address.", Toast.LENGTH_LONG).show()
            return false
        }
        if(binding.etPassword.text.toString().trim() == ""){
            Toast.makeText(baseContext, "Please enter a password.", Toast.LENGTH_LONG).show()
            return false
        }
        if(binding.etPassword.text.toString().trim() != binding.etConfirmPassword.text.toString().trim()){
            Toast.makeText(baseContext, "Make sure both password match and please try again.", Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    private fun registerUser() {
        if(validateInput()){
            val firstName = binding.etFirstName.text.toString().trim()
            val mobile = binding.etMobile.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val registerRequestData = JSONObject()
            registerRequestData.put("firstName", firstName)
            registerRequestData.put("mobile", mobile)
            registerRequestData.put("email", email)
            registerRequestData.put("password", password)

            val request = JsonObjectRequest(
                Request.Method.PUT,
                "https://grocery-second-app.herokuapp.com/api/auth/register",
                registerRequestData,
                Response.Listener { response ->
                    if(!response.getBoolean("error")){
                        Toast.makeText(baseContext, "Account registered successfully.", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(baseContext, "Account registration failed: ${response.getString("message")}", Toast.LENGTH_LONG).show()
                    }
                },
                Response.ErrorListener { error ->
                    error.printStackTrace()
                    Toast.makeText(baseContext, "Error is: $error", Toast.LENGTH_LONG).show()
                }

        )}else return
    }
}