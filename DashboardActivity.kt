package com.example.onlineshoppingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.MessageQueue
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.onlineshoppingproject.data.Category
import com.example.onlineshoppingproject.data.CategoryResponse
import com.example.onlineshoppingproject.databinding.ActivityDashboardBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception

class DashboardActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding
    lateinit var queue: RequestQueue
    lateinit var adapter: CategoryAdapter
    lateinit var categories: List<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(baseContext)
        binding.rvCategories.layoutManager = LinearLayoutManager(this)

        loadCategories()

        binding.rvCategories.setOnClickListener {

        }

    }

    private fun loadCategories() {
        val url = "${Constants.API_BASE_URL}/Category"
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                apiResponse: String ->
                val typeToken = object : TypeToken<CategoryResponse>() {}
                val gson: Gson = Gson()
                try {
                    val response: CategoryResponse = gson.fromJson(apiResponse, typeToken.type)
                    if(response.message == "Success") {
                        categories = response.categories
                        adapter = CategoryAdapter(categories)
                        binding.rvCategories.adapter = adapter
                    } else {
                        Toast.makeText(baseContext, "Failed to load categories. Please retry.", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(baseContext, "Error is : ${e.toString()}", Toast.LENGTH_LONG).show()
                }
            },
            {
                    error ->
                error.printStackTrace()
                Toast.makeText(baseContext, "Error is : $error", Toast.LENGTH_LONG).show()
            }
        )

        queue.add(request)
    }
}