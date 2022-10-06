package com.finalproject.cinephile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.finalproject.cinephile.BuildConfig
import com.finalproject.cinephile.R
import com.finalproject.cinephile.adapter.TvShowsVerticalAdapter.TvShowsVerticalVH
import com.finalproject.cinephile.data.model.TvShow
import com.finalproject.cinephile.databinding.ItemMovieVerticalBinding

class TvShowsVerticalAdapter(
    private val callback: OnItemClicked<TvShow>,
    itemCallback: DiffUtil.ItemCallback<TvShow>
) : RecyclerView.Adapter<TvShowsVerticalVH>() {

    val differ = AsyncListDiffer(this, itemCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsVerticalVH =
        TvShowsVerticalVH(
            ItemMovieVerticalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TvShowsVerticalVH, position: Int) {
        holder.bindItem(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class TvShowsVerticalVH(private val binding: ItemMovieVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(tvShow: TvShow) {
            binding.movieTitle.text = tvShow.title
            binding.movieRating.text = tvShow.rating.toString()
            binding.movieOverview.text = tvShow.overview
            Glide.with(itemView)
                .load(BuildConfig.TMDB_IMAGE_BASE_URL + tvShow.backdrop)
                .placeholder(R.drawable.ic_undraw_images)
                .error(R.drawable.ic_undraw_404).centerCrop()
                .into(binding.moviePoster)

            itemView.setOnClickListener {
                callback.onItemClicked(tvShow)
            }
        }
    }
}