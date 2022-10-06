package com.finalproject.cinephile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.finalproject.cinephile.BuildConfig
import com.finalproject.cinephile.R
import com.finalproject.cinephile.adapter.MoviesVerticalAdapter.MoviesVerticalVH
import com.finalproject.cinephile.data.model.Movie
import com.finalproject.cinephile.databinding.ItemMovieVerticalBinding

class MoviesVerticalAdapter(
    private val callback: OnItemClicked<Movie>,
    differCallback: DiffUtil.ItemCallback<Movie>,
) : RecyclerView.Adapter<MoviesVerticalVH>() {

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesVerticalVH =
        MoviesVerticalVH(
            ItemMovieVerticalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesVerticalVH, position: Int) {
        holder.bindMovieItem(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class MoviesVerticalVH(private val binding: ItemMovieVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindMovieItem(movie: Movie) {
            binding.apply {
                movieTitle.text = movie.title
                movieRating.text = movie.rating.toString()
                movieOverview.text = movie.overview
                Glide.with(itemView)
                    .load(BuildConfig.TMDB_IMAGE_BASE_URL + movie.backdrop)
                    .placeholder(R.drawable.ic_undraw_images)
                    .error(R.drawable.ic_undraw_404).centerCrop()
                    .into(moviePoster)

                root.setOnClickListener {
                    callback.onItemClicked(movie)
                }
            }
        }

    }
}