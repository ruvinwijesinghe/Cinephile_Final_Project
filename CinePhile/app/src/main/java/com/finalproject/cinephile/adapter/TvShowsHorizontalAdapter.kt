package com.finalproject.cinephile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.finalproject.cinephile.BuildConfig
import com.finalproject.cinephile.R
import com.finalproject.cinephile.adapter.TvShowsHorizontalAdapter.TvShowsHorizontalVH
import com.finalproject.cinephile.data.model.TvShow
import com.finalproject.cinephile.databinding.ItemMovieHorizontalBinding

class TvShowsHorizontalAdapter(
    private val callback: OnItemClicked<TvShow>,
    itemCallback: DiffUtil.ItemCallback<TvShow>
) : RecyclerView.Adapter<TvShowsHorizontalVH>() {

    val differ = AsyncListDiffer(this, itemCallback)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TvShowsHorizontalVH =
        TvShowsHorizontalVH(
            ItemMovieHorizontalBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )

    override fun onBindViewHolder(holder: TvShowsHorizontalVH, position: Int) {
        holder.bindItem(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class TvShowsHorizontalVH(private val binding: ItemMovieHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(tvShow: TvShow) {
            binding.movieTitle.text = tvShow.title
            Glide.with(itemView)
                .load(BuildConfig.TMDB_IMAGE_BASE_URL + tvShow.posterPath)
                .placeholder(R.drawable.ic_undraw_images)
                .error(R.drawable.ic_undraw_404).centerCrop()
                .into(binding.moviePoster)

            itemView.setOnClickListener {
                callback.onItemClicked(tvShow)
            }
        }
    }
}