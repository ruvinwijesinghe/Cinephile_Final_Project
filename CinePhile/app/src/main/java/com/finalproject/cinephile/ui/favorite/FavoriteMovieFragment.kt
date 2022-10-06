package com.finalproject.cinephile.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.finalproject.cinephile.adapter.MoviesVerticalAdapter
import com.finalproject.cinephile.databinding.FragmentTabBinding
import com.finalproject.cinephile.ui.viewmodel.FavoritesViewModel
import com.finalproject.cinephile.utils.BaseFragment
import com.finalproject.cinephile.utils.LayoutManagerUtil.getVerticalLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteMovieFragment : BaseFragment() {

    private var _binding: FragmentTabBinding? = null
    private val binding: FragmentTabBinding
        get() = _binding!!

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    @Inject
    lateinit var adapter: MoviesVerticalAdapter

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
        showLoading(true)

        binding.rvMovies.layoutManager = getVerticalLayoutManager(requireContext())
        binding.rvMovies.adapter = adapter

        observeData()
        attachTouchHelper()
    }

    private fun observeData() {
        favoritesViewModel.favoriteMovies.observe(viewLifecycleOwner) { movieItems ->
            movieItems?.let {
                adapter.differ.submitList(movieItems)
                if (movieItems.isEmpty()) {
                    binding.tvNull.visibility = View.VISIBLE
                } else {
                    binding.tvNull.visibility = View.GONE
                }
                showLoading(false)
            }
        }
    }

    private fun attachTouchHelper() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                val movie = adapter.differ.currentList[viewHolder.adapterPosition]
                favoritesViewModel.deleteFavMovie(movie)
                showMessageSnackbar(
                    "${movie.title}  deleted from movies favorite list."
                )
            }
        }).attachToRecyclerView(binding.rvMovies)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}