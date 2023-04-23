package com.easyprog.android.moviemate.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.easyprog.android.moviemate.data.model.Movie

abstract class BaseAdapter<T : ViewBinding>(private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> T) :
    RecyclerView.Adapter<BaseAdapter<T>.BaseViewHolder>(), View.OnClickListener {

    var movieList: List<Movie> = emptyList()
        set(newValue) {
            field = newValue
            notifyItemRangeInserted(0, newValue.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding: T = bindingInflater.invoke(LayoutInflater.from(parent.context), parent, false)

        binding.root.setOnClickListener(this)

        return BaseViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    inner class BaseViewHolder(val binding: T) : RecyclerView.ViewHolder(binding.root)
}