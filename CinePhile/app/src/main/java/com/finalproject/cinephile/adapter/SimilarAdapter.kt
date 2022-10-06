package com.finalproject.cinephile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.finalproject.cinephile.BuildConfig
import com.finalproject.cinephile.R
import com.finalproject.cinephile.adapter.SimilarAdapter.SimilarViewHolder
import com.finalproject.cinephile.data.model.Similar
import com.finalproject.cinephile.databinding.ItemSimilarBinding
import com.finalproject.cinephile.utils.Constants.MOVIE_TYPE
import com.finalproject.cinephile.utils.Constants.TV_TYPE

// TODO: 9/1/20 Add to adapter module
class SimilarAdapter(
    private val similarList: MutableList<Similar>,
    private val type: String,
    private val onItemClicked: OnItemClicked<Similar>
) : RecyclerView.Adapter<SimilarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        return SimilarViewHolder(
            ItemSimilarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        holder.bindSimilarItem(similarList[position])
    }

    override fun getItemCount(): Int = similarList.size

    fun setSimilarList(similarList: List<Similar>) {
        this.similarList.clear()
        this.similarList.addAll(similarList)
        notifyDataSetChanged()
    }

    inner class SimilarViewHolder(private val binding: ItemSimilarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindSimilarItem(similar: Similar) {
            if (type == MOVIE_TYPE) {
                binding.titleSimilar.text = similar.title
            } else if (type == TV_TYPE) {
                binding.titleSimilar.text = similar.name
            }

            binding.ratingSimilar.text = similar.rating.toString()
            Glide.with(itemView)
                .load(BuildConfig.TMDB_IMAGE_BASE_URL + similar.posterPath)
                .placeholder(R.drawable.ic_undraw_images)
                .error(R.drawable.ic_undraw_404)
                .centerCrop()
                .into(binding.posterSimilar)
            binding.root.setOnClickListener {
                onItemClicked.onItemClicked(similar)
            }
        }
    }
}