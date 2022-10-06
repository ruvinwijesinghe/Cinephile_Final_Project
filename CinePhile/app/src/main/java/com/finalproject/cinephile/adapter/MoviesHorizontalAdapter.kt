package com.finalproject.cinephile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.finalproject.cinephile.BuildConfig
import com.finalproject.cinephile.R
import com.finalproject.cinephile.adapter.MoviesHorizontalAdapter.MoviesHorizontalVH
import com.finalproject.cinephile.data.model.Movie
import com.finalproject.cinephile.databinding.ItemMovieHorizontalBinding

class MoviesHorizontalAdapter(
    private val onItemClicked: OnItemClicked<Movie>,
    differCallback: DiffUtil.ItemCallback<Movie>,
) : RecyclerView.Adapter<MoviesHorizontalVH>() {

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoviesHorizontalVH =
        MoviesHorizontalVH(
            ItemMovieHorizontalBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesHorizontalVH, position: Int) {
        holder.bindMovieItem(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class MoviesHorizontalVH(private val binding: ItemMovieHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindMovieItem(movie: Movie) {
            binding.apply {
                movieTitle.text = movie.title
                Glide.with(itemView)
                    .load(BuildConfig.TMDB_IMAGE_BASE_URL + movie.posterPath)
                    .placeholder(R.drawable.ic_undraw_images)
                    .error(R.drawable.ic_undraw_404)
                    .centerCrop()
                    .into(moviePoster)
                root.setOnClickListener {
                    onItemClicked.onItemClicked(movie)
                }
            }
        }
    }
}