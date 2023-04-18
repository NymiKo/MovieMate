package com.easyprog.android.moviemate.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.adapters.MoviesByCategoryAdapter.MoviesByCategoryViewHolder
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.ItemCategoryMovieBinding
import com.easyprog.android.moviemate.utils.loadImage

class MoviesByCategoryAdapter(
    private val actionListener: BaseActionListener
) : RecyclerView.Adapter<MoviesByCategoryViewHolder>(), View.OnClickListener {

    var movieList: List<Movie> = emptyList()
        set(newValue) {
            field = newValue
            notifyItemRangeInserted(0, newValue.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesByCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryMovieBinding.inflate(layoutInflater, parent, false)

        binding.root.setOnClickListener(this)

        return MoviesByCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesByCategoryViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            root.tag = movie.id
            textElementId.text = position.plus(1).toString()
            imageMovieAvatar.loadImage(movie.image)
            textMovieName.text = movie.name
        }
    }

    override fun getItemCount(): Int = movieList.size

    class MoviesByCategoryViewHolder(val binding: ItemCategoryMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View?) {
        val idMovie = v?.tag.toString()
        actionListener.onMovieClick(idMovie)
    }
}