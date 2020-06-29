package com.wassim.showcase.features.favorite.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.wassim.showcase.R
import com.wassim.showcase.features.albums.list.AlbumsUiState
import kotlinx.android.synthetic.main.profile_favorite_albums.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class FavoriteAlbumsFragment : Fragment(R.layout.profile_favorite_albums) {

    private val viewModel: FavoriteAlbumsViewModel by inject()

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

    private fun updateUi(uiState: AlbumsUiState) {
        when (uiState) {
            is AlbumsUiState.Content -> {
                favoriteAlbumsCount.text = requireContext().getString(
                    R.string.saved_albums_count,
                    uiState.list.size
                )
            }
            is AlbumsUiState.Error -> {
                Timber.e(requireContext().getString(uiState.resId))
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    requireContext().getString(uiState.resId),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            AlbumsUiState.Loading -> Unit
        }
    }
}
