package com.example.groceryapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.example.groceryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
    private const val LAUNCH_LOGIN_SCREEN: Int = 200
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val twoSeconds = 1000L * 2
        handler.sendEmptyMessageDelayed(LAUNCH_LOGIN_SCREEN, twoSeconds)

    }

    val handler = object: Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg.what == LAUNCH_LOGIN_SCREEN){
                startActivity(Intent(baseContext, LoginActivity::class.java))
            }
        }
    }
}