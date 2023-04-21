package com.easyprog.android.moviemate.adapters.new_movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.android.moviemate.adapters.BaseActionListener
import com.easyprog.android.moviemate.data.model.Movie
import com.easyprog.android.moviemate.databinding.ItemRecommendedMovieBinding
import com.easyprog.android.moviemate.utils.loadImage

class NewMovieAdapter(
    private val actionListener: BaseActionListener
): RecyclerView.Adapter<NewMovieAdapter.NewMovieViewHolder>(), View.OnClickListener {

    var movieList: List<Movie> = emptyList()
        set(newValue) {
            field = newValue
            notifyItemRangeInserted(0, newValue.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecommendedMovieBinding.inflate(layoutInflater, parent, false)

        binding.root.setOnClickListener(this)

        return NewMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewMovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            root.tag = movie.id
            imageMovieAvatar.loadImage(movie.image)
            textMovieName.text = movie.name
        }
    }

    override fun getItemCount(): Int = movieList.size

    class NewMovieViewHolder(val binding: ItemRecommendedMovieBinding): RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val idMovie = v.tag.toString()
        actionListener.onMovieClick(idMovie)
    }
}