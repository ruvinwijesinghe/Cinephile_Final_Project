package com.finalproject.cinephile.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.finalproject.cinephile.adapter.TvShowsVerticalAdapter
import com.finalproject.cinephile.data.vo.Status
import com.finalproject.cinephile.databinding.FragmentTabBinding
import com.finalproject.cinephile.ui.viewmodel.SearchViewModel
import com.finalproject.cinephile.utils.BaseFragment
import com.finalproject.cinephile.utils.LayoutManagerUtil.getVerticalLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchTvFragment : BaseFragment() {

    private var _binding: FragmentTabBinding? = null
    private val binding: FragmentTabBinding
        get() = _binding!!

    private val viewModel: SearchViewModel by activityViewModels()

    @Inject
    lateinit var adapter: TvShowsVerticalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvMovies.layoutManager = getVerticalLayoutManager(requireContext())
            rvMovies.adapter = adapter
        }
        observeData()
    }

    private fun observeData() {
        viewModel.searchTvShows.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    adapter.differ.submitList(response.data?.tvShowItems)
                    showLoading(false)

                }
                Status.ERROR -> showMessageSnackbar(response.message!!)
                Status.LOADING -> showLoading(true)
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}