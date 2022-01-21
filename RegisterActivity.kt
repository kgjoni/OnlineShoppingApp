package com.example.onlineshoppingproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.onlineshoppingproject.databinding.ActivityRegisterBinding
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var queue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        queue = Volley.newRequestQueue(baseContext)
        setUpEvents()
    }

    private fun setUpEvents() {
        binding.btnRegister.setOnClickListener{
            registerUser()
        }

        binding.btnAlreadyHaveAnAccount.setOnClickListener{
            cancelRegister()
        }
    }

    private fun registerUser() {
        val endpoint = "User/register"
        val url = "${Constants.API_BASE_URL}$endpoint"
        val data = JSONObject()

        val name = binding.etName.text.toString()
        val mobile = binding.etMobileNo.text.toString()
        val password = binding.etPassword.text.toString()
        val email = binding.etEmail.text.toString()

        data.put("full_name", name)
        data.put("mobile_no", mobile)
        data.put("email_id", email)
        data.put("password", password)

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            data,
            {
                response: JSONObject ->
                val message = response.getString("message")
                Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
            },
            {
                error: VolleyError ->
                Toast.makeText(baseContext, "Error is : $error", Toast.LENGTH_LONG).show()
                error.printStackTrace()
            }
        )
        queue.add(request)
    }

    private fun cancelRegister() {
        startActivity(Intent(baseContext, LoginActivity::class.java))
        finish()
    }

}