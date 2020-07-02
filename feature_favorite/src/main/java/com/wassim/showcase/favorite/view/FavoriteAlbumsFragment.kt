package com.wassim.showcase.favorite.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.wassim.showcase.favorite.FavoriteAlbumsUiState
import com.wassim.showcase.favorite.R
import com.wassim.showcase.favorite.di.inject
import kotlinx.android.synthetic.main.profile_favorite_albums.*
import timber.log.Timber
import javax.inject.Inject

class FavoriteAlbumsFragment : Fragment(R.layout.profile_favorite_albums) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FavoriteAlbumsViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // inject dagger component
        inject(this)
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
