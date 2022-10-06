package com.finalproject.cinephile.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.finalproject.cinephile.adapter.EpisodeAdapter
import com.finalproject.cinephile.data.model.Season
import com.finalproject.cinephile.databinding.ActivitySeasonsBinding
import com.finalproject.cinephile.ui.viewmodel.SeasonsViewModel
import com.finalproject.cinephile.utils.LayoutManagerUtil.getVerticalLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SeasonsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeasonsBinding
    private val viewModel: SeasonsViewModel by viewModels()

    @Inject
    lateinit var adapter: EpisodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeasonsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.seasonToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvEpisode.layoutManager = getVerticalLayoutManager(this)
        binding.rvEpisode.adapter = adapter

        viewModel.seasons.observe(this) { episodes: Season ->
            supportActionBar?.title = episodes.name
            adapter.differ.submitList(episodes.episodes)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}