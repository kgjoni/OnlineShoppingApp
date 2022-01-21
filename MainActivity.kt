package com.example.onlineshoppingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlineshoppingproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences("login_details", MODE_PRIVATE)

        if(pref.contains("token")) {
            startActivity(Intent(baseContext, DashboardActivity::class.java))
            finish()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(baseContext, RegisterActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            startActivity((Intent(baseContext, LoginActivity::class.java)))
            finish()
        }
    }
}