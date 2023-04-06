package com.easyprog.android.moviemate.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.adapters.CategoriesAdapter.CategoryViewHolder
import com.easyprog.android.moviemate.databinding.ItemCategoriesBinding

class CategoriesAdapter: RecyclerView.Adapter<CategoryViewHolder>(){

    var categoriesList: List<String> = listOf("Фильмы", "Сериалы", "Ужасы", "Триллер", "Драма", "Детектив", "Боевик", "Коммедия", "Аниме")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoriesBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoriesList[position]
        holder.binding.buttonCategory.text = category
    }

    override fun getItemCount(): Int = categoriesList.size

    class CategoryViewHolder(val binding: ItemCategoriesBinding): RecyclerView.ViewHolder(binding.root)
}