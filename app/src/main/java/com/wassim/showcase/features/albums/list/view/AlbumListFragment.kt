package com.wassim.showcase.features.albums.list.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import com.google.android.material.snackbar.Snackbar
import com.wassim.showcase.R
import com.wassim.showcase.features.albums.AlbumUiModel
import com.wassim.showcase.features.albums.list.AlbumsUiState
import kotlinx.android.synthetic.main.albumlist_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class AlbumListFragment : Fragment(R.layout.albumlist_fragment) {

    private val imageLoader: ImageLoader by inject()
    private val albumListViewModel: AlbumListViewModel by viewModel()
    private lateinit var albumsAdapter: AlbumsAdapter

    /**
     * We call load albums in onCreate because the Fragment view got destroyed
     * on every navigation.
     * This way we call the initial load albums only one time per instance.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumListViewModel.loadAlbums()
        Timber.d("loadAlbums called")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.apply {
            albumsAdapter =
                AlbumsAdapter(
                    imageLoader
                ) { album ->
                    openAlbumDetails(album)
                }
            adapter = albumsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        albumListViewModel.uiState.observe(viewLifecycleOwner, Observer {
            updateUI(it)
        })
    }

    private fun openAlbumDetails(album: AlbumUiModel) {
        val action =
            AlbumListFragmentDirections.actionNext(
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
