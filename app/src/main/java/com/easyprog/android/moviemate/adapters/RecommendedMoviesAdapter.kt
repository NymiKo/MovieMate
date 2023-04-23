package com.easyprog.android.moviemate.adapters

import android.view.View
import com.easyprog.android.moviemate.databinding.ItemRecommendedMovieBinding
import com.easyprog.android.moviemate.utils.loadImage

class RecommendedMoviesAdapter(
    private val actionListener: BaseActionListener
) : BaseAdapter<ItemRecommendedMovieBinding>(ItemRecommendedMovieBinding::inflate) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            root.tag = movie.id
            imageMovieAvatar.loadImage(movie.image)
            textMovieName.text = movie.name
        }
    }

    override fun onClick(v: View?) {
        val idMovie = v?.tag.toString()
        actionListener.onMovieClick(idMovie)
    }
}