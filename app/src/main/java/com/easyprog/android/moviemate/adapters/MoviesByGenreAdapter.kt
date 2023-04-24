package com.easyprog.android.moviemate.adapters

import android.view.View
import com.easyprog.android.moviemate.databinding.ItemGenreMovieBinding
import com.easyprog.android.moviemate.utils.loadImage

class MoviesByGenreAdapter(
    private val actionListener: BaseActionListener
) : BaseAdapter<ItemGenreMovieBinding>(ItemGenreMovieBinding::inflate) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            root.tag = movie.id
            textElementId.text = position.plus(1).toString()
            imageMovieAvatar.loadImage(movie.image)
            textMovieName.text = movie.name
        }
    }

    override fun onClick(v: View?) {
        val idMovie = v?.tag.toString()
        actionListener.onMovieClick(idMovie)
    }
}