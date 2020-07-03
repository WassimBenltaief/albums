package com.wassim.showcase.featurealbums.view.list.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import com.google.android.material.snackbar.Snackbar
import com.wassim.showcase.featurealbums.R
import com.wassim.showcase.featurealbums.di.inject
import com.wassim.showcase.featurealbums.view.AlbumUiModel
import com.wassim.showcase.featurealbums.view.list.AlbumsUiState
import javax.inject.Inject
import kotlinx.android.synthetic.main.albumlist_fragment.*
import timber.log.Timber

class AlbumListFragment : Fragment(R.layout.albumlist_fragment) {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var albumsAdapter: AlbumsAdapter

    private val albumListViewModel by viewModels<AlbumListViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject(this)
        super.onCreate(savedInstanceState)
        albumListViewModel.loadAlbums()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.apply {
            albumsAdapter =
                AlbumsAdapter(
                    imageLoader
                ) { album -> openAlbumDetails(album) }
            adapter = albumsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        albumListViewModel.uiState.observe(viewLifecycleOwner, Observer {
            updateUI(it)
        })
    }

    private fun openAlbumDetails(album: AlbumUiModel) {
        val action = AlbumListFragmentDirections.actionNext(
            album.id,
            album.name,
            album.artist
        )
        findNavController().navigate(action)
    }

    private fun updateUI(albumsUiState: AlbumsUiState) {
        when (albumsUiState) {
            is AlbumsUiState.Content -> {
                progressBar.visibility = View.GONE
                recycler.visibility = View.VISIBLE
                albumsAdapter.albums = albumsUiState.list
                Timber.d("content")
            }
            is AlbumsUiState.Error -> {
                progressBar.visibility = View.GONE
                recycler.visibility = View.GONE

                Timber.e(requireContext().getString(albumsUiState.resId))
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    requireContext().getString(albumsUiState.resId),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            is AlbumsUiState.Loading -> {
                recycler.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
        }
    }
}
