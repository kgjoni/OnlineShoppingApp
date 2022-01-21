package com.example.onlineshoppingproject

import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshoppingproject.data.Category
import com.example.onlineshoppingproject.databinding.ViewHolderCategoryBinding
import com.squareup.picasso.Picasso

class CategoryViewHolder(val binding: ViewHolderCategoryBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category) {
        binding.tvCategory.text = category.category_name
        Picasso.get().load(category.category_image_url).into(binding.ivPhoto)
    }
}