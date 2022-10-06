package com.finalproject.cinephile.di

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.DiffUtil
import com.finalproject.cinephile.adapter.CastAdapter
import com.finalproject.cinephile.adapter.EpisodeAdapter
import com.finalproject.cinephile.adapter.OnItemClicked
import com.finalproject.cinephile.adapter.SimilarAdapter
import com.finalproject.cinephile.data.model.Cast
import com.finalproject.cinephile.data.model.Episode
import com.finalproject.cinephile.data.model.Similar
import com.finalproject.cinephile.ui.detail.MovieDetailsActivity
import com.finalproject.cinephile.ui.detail.TvShowDetailsActivity
import com.finalproject.cinephile.ui.viewmodel.MovieDetailsViewModel.Companion.MOVIE_ID_KEY
import com.finalproject.cinephile.ui.viewmodel.TvShowDetailsViewModel.Companion.TV_SHOW_ID_KEY
import com.finalproject.cinephile.utils.Constants.MOVIE_TYPE
import com.finalproject.cinephile.utils.Constants.TV_TYPE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideDifferCastCallback() =
        object : DiffUtil.ItemCallback<Cast>() {
            override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem == newItem
            }
        }

    @Provides
    fun provideCastAdapter(itemCallback: DiffUtil.ItemCallback<Cast>) =
        CastAdapter(itemCallback)

    @MovieQualifier
    @Provides
    fun provideMovieSimilarOnItemClick(activity: Activity) =
        object : OnItemClicked<Similar> {
            override fun onItemClicked(similar: Similar) {
                Intent(activity, MovieDetailsActivity::class.java).also {
                    it.putExtra(MOVIE_ID_KEY, similar.id)
                    activity.startActivity(it)
                }
            }
        }


    @MovieQualifier
    @Provides
    fun provideMovieSimilarAdapter(@MovieQualifier callback: OnItemClicked<Similar>) =
        SimilarAdapter(mutableListOf(), MOVIE_TYPE, callback)

    @TvShowQualifier
    @Provides
    fun provideTvShowSimilarOnItemClick(activity: Activity) =
        object : OnItemClicked<Similar> {
            override fun onItemClicked(similar: Similar) {
                Intent(activity, TvShowDetailsActivity::class.java).also {
                    it.putExtra(TV_SHOW_ID_KEY, similar.id)
                    activity.startActivity(it)
                }
            }
        }

    @TvShowQualifier
    @Provides
    fun provideTvShowSimilarAdapter(@TvShowQualifier callback: OnItemClicked<Similar>) =
        SimilarAdapter(mutableListOf(), TV_TYPE, callback)


    @Provides
    fun provideEpisodeDifferCallback() =
        object : DiffUtil.ItemCallback<Episode>() {
            override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
                return oldItem == newItem
            }
        }

    @Provides
    fun provideEpisodeAdapter(itemCallback: DiffUtil.ItemCallback<Episode>) =
        EpisodeAdapter(itemCallback)

}