package com.example.groceryapp.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
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
            Request.Method.POST,
            "https://grocery-second-app.herokuapp.com/api/auth/login",
            loginRequestData,
            Response.Listener<JSONObject>{ response ->
                Log.d("response", response.toString())
                if(response.has("error") && response.getBoolean("error")){
                    Toast.makeText(baseContext, "Invalid Login Information, please try again",
                        Toast.LENGTH_LONG).show()
                }else{
                    if(binding.cbxRememberMe.isChecked()){
                        val sharedPref = getSharedPreferences("app_settings", MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPref.edit()
                        val firstName = response.getJSONObject("user").getString("firstName")
                        val userId = response.getJSONObject("user").getString("_id")
                        editor.putString("firstName", firstName)
                        editor.putString("userId", userId)
                        editor.apply()
                    }
                    startActivity(Intent(baseContext, HomeActivity::class.java))
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                Toast.makeText(baseContext, "Error: $error", Toast.LENGTH_LONG).show()
            }
        )
        request.retryPolicy = DefaultRetryPolicy(10 * 1000, 1, 1.0f)
        requestQueue.add(request)
    }
}