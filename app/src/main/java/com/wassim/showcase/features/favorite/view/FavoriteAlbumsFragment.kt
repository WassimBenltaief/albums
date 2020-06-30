package com.wassim.showcase.features.favorite.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.wassim.showcase.R
import com.wassim.showcase.features.favorite.FavoriteAlbumsUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.profile_favorite_albums.*
import timber.log.Timber

@AndroidEntryPoint
class FavoriteAlbumsFragment : Fragment(R.layout.profile_favorite_albums) {

    private val viewModel: FavoriteAlbumsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadFavoriteAlbums()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            updateUi(it)
        })
    }

    private fun updateUi(uiState: FavoriteAlbumsUiState) {
        when (uiState) {
            is FavoriteAlbumsUiState.Content -> {
                favoriteAlbumsCount.text = requireContext().getString(
                    R.string.saved_albums_count,
                    uiState.data.count
                )
            }
            is FavoriteAlbumsUiState.Error -> {
                Timber.e(requireContext().getString(uiState.resId))
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    requireContext().getString(uiState.resId),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            FavoriteAlbumsUiState.Loading -> Unit
        }
    }
}
