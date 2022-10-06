package com.finalproject.cinephile.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.finalproject.cinephile.adapter.TvShowsHorizontalAdapter
import com.finalproject.cinephile.databinding.FragmentTvShowBinding
import com.finalproject.cinephile.ui.viewmodel.TvShowsViewModel
import com.finalproject.cinephile.utils.BaseFragment
import com.finalproject.cinephile.utils.LayoutManagerUtil.getHorizontalLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TvShowFragment : BaseFragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding: FragmentTvShowBinding
        get() = _binding!!

    private val tvShowsViewModel: TvShowsViewModel by activityViewModels()

    @Inject
    lateinit var popularAdapter: TvShowsHorizontalAdapter

    @Inject
    lateinit var topRatedAdapter: TvShowsHorizontalAdapter

    @Inject
    lateinit var onTheAirAdapter: TvShowsHorizontalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvPopular.layoutManager = getHorizontalLayoutManager(requireContext())
            rvPopular.adapter = popularAdapter

            rvTopRated.layoutManager = getHorizontalLayoutManager(requireContext())
            rvTopRated.adapter = topRatedAdapter

            rvNowPlaying.layoutManager = getHorizontalLayoutManager(requireContext())
            rvNowPlaying.adapter = onTheAirAdapter
        }
    }

    private fun observeData() {
        tvShowsViewModel.popularTv.observe(viewLifecycleOwner) { response ->
            response.data?.tvShowItems?.let { list ->
                handleResponseResult(
                    response,
                    list,
                    popularAdapter.differ,
                    binding.popularShimmerContainer
                )
            }
        }

        tvShowsViewModel.topRatedTv.observe(viewLifecycleOwner) { response ->
            response.data?.tvShowItems?.let { list ->
                handleResponseResult(
                    response,
                    list,
                    topRatedAdapter.differ,
                    binding.topRatedShimmerContainer
                )
            }
        }

        tvShowsViewModel.nowPlayingTv.observe(viewLifecycleOwner) { response ->
            response.data?.tvShowItems?.let { list ->
                handleResponseResult(
                    response,
                    list,
                    onTheAirAdapter.differ,
                    binding.nowPlayingShimmerContainer
                )
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}