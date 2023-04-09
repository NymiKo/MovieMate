package com.easyprog.android.moviemate.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.adapters.CategoriesAdapter.CategoryViewHolder
import com.easyprog.android.moviemate.adapters.categories.CategoriesActionListener
import com.easyprog.android.moviemate.databinding.ItemCategoriesBinding

class CategoriesAdapter(
    private val actionListener: CategoriesActionListener
): RecyclerView.Adapter<CategoryViewHolder>(), View.OnClickListener {

    var categoriesList: List<String> = listOf("Фэнтези", "Сериалы", "Ужасы", "Триллер", "Драма", "Детектив", "Боевик", "Комедия", "Фантастика")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoriesBinding.inflate(layoutInflater, parent, false)

        binding.buttonCategory.setOnClickListener(this)

        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoriesList[position]
        holder.binding.buttonCategory.tag = category
        holder.binding.buttonCategory.text = category
    }

    override fun getItemCount(): Int = categoriesList.size

    class CategoryViewHolder(val binding: ItemCategoriesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View?) {
        val category = v?.tag as String
        actionListener.categoryClick(category)
    }
}