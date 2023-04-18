package com.easyprog.android.moviemate.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.adapters.RecommendedMoviesAdapter.RecommendedMoviesViewHolder
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.ItemRecommendedMovieBinding
import com.easyprog.android.moviemate.utils.loadImage

class RecommendedMoviesAdapter(
    private val actionListener: BaseActionListener
) : RecyclerView.Adapter<RecommendedMoviesViewHolder>(), View.OnClickListener {

    var recommendedMoviesList: List<Movie> = emptyList()
        set(newValue) {
            field = newValue
            notifyItemRangeInserted(0, newValue.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecommendedMovieBinding.inflate(layoutInflater, parent, false)

        binding.root.setOnClickListener(this)

        return RecommendedMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendedMoviesViewHolder, position: Int) {
        val movie = recommendedMoviesList[position]
        holder.binding.apply {
            root.tag = movie.id
            imageMovieAvatar.loadImage(movie.image)
            textMovieName.text = movie.name
        }
    }

    override fun getItemCount(): Int = recommendedMoviesList.size

    class RecommendedMoviesViewHolder(val binding: ItemRecommendedMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View?) {
        val idMovie = v?.tag.toString()
        actionListener.onMovieClick(idMovie)
    }
}