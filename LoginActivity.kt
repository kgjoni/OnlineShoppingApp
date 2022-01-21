package com.example.onlineshoppingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.onlineshoppingproject.databinding.ActivityLoginBinding
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var queue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        queue = Volley.newRequestQueue(baseContext)
        setUpEvents()
    }

    private fun setUpEvents() {
        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.btnCancel.setOnClickListener{
            cancelLogin()
        }
    }

    private fun loginUser() {
        val endpoint = "User/auth"
        val url = "${Constants.API_BASE_URL}$endpoint"
        val data = JSONObject()

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        data.put("email_id", email)
        data.put("password", password)

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            data,
            { it ->
                if(it.get("status") == 0) {
                    val user: JSONObject = it.getJSONObject("user")
                    val userName = user.getString("full_name")
                    val mobileNo = user.getString("mobile_no")
                    val emailID = user.getString("email_id")

                    val pref = getSharedPreferences("login_details", MODE_PRIVATE)
                    val editor = pref.edit()

                    editor.putString("full_name", userName)
                    editor.putString("mobile_no", mobileNo)
                    editor.putString("email_id", emailID)
                    editor.apply()

                    startActivity(Intent(baseContext, DashboardActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(baseContext, "Failed to login. Please retry.", Toast.LENGTH_LONG).show()
                }

            },
            {
                error: VolleyError ->
                Toast.makeText(baseContext, "Error is : $error", Toast.LENGTH_LONG).show()
                error.printStackTrace()
            }
        )
        queue.add(request)
    }

    private fun cancelLogin() {
        startActivity(Intent(baseContext, RegisterActivity::class.java))
        finish()
    }
}