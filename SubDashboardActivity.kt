package com.example.onlineshoppingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.onlineshoppingproject.data.Category
import com.example.onlineshoppingproject.data.Subcategory
import com.example.onlineshoppingproject.databinding.ActivityDashboardBinding
import com.example.onlineshoppingproject.databinding.ActivitySubDashboardBinding

class SubDashboardActivity : AppCompatActivity() {
    lateinit var binding: ActivitySubDashboardBinding
    lateinit var queue: RequestQueue
    lateinit var adapter: CategoryAdapter
    lateinit var sunCategories: List<Subcategory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(baseContext)

        loadSubCategories()

    }

    private fun loadSubCategories() {
        TODO("Not yet implemented")
    }
}