package com.wassim.showcase.featurealbums.view.item.view

import android.os.Bundle
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.request.LoadRequest
import coil.transform.GrayscaleTransformation
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.wassim.showcase.di.DynamicFeaturesDependencies
import com.wassim.showcase.featurealbums.R
import com.wassim.showcase.featurealbums.di.DaggerAlbumsComponent
import com.wassim.showcase.featurealbums.view.AlbumUiModel
import com.wassim.showcase.featurealbums.view.item.SingleAlbumUiState
import dagger.hilt.android.EntryPointAccessors
import kotlinx.android.synthetic.main.album_fragment.*
import javax.inject.Inject

class AlbumFragment : Fragment(R.layout.album_fragment) {

    @Inject
    lateinit var imageLoader: ImageLoader
    private val args: AlbumFragmentArgs by navArgs()
    private val albumViewModel: AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAlbumsComponent.factory().create(
            EntryPointAccessors.fromApplication(requireContext(), DynamicFeaturesDependencies::class.java)
        ).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewListeners()
        albumViewModel.uiState.observe(viewLifecycleOwner, Observer {
            updateUI(it)
        })

        albumViewModel.findAlbum(
            albumId = args.albumId,
            album = args.album,
            artist = args.artist
        )
    }

    private fun setupViewListeners() {
        var isToolbarShown = false
        // scroll change listener begins at Y = 0 when image is fully collapsed
        albumDetailScrollview.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                // User scrolled past image to height of toolbar and the title text is
                // underneath the toolbar, so the toolbar should be shown.
                val shouldShowToolbar = scrollY > toolbar.height

                // The new state of the toolbar differs from the previous state; update
                // appbar and toolbar attributes.
                if (isToolbarShown != shouldShowToolbar) {
                    isToolbarShown = shouldShowToolbar

                    // Use shadow animator to add elevation if toolbar is shown
                    appbar.isActivated = shouldShowToolbar

                    // Show the plant name if toolbar is shown
                    toolbarLayout.isTitleEnabled = shouldShowToolbar
                }
            }
        )

        fab.setOnClickListener {
            markAsFavorite()
            hideAppBarFab(fab)
        }

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun hideAppBarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }

    private fun markAsFavorite() {
        albumViewModel.markAsFavorite()
    }

    private fun updateUI(uiState: SingleAlbumUiState) {
        when (uiState) {
            is SingleAlbumUiState.Content -> renderContent(uiState.album)
            is SingleAlbumUiState.SnackBar -> renderSnackBar(uiState.resId)
            SingleAlbumUiState.Loading -> Unit
        }
    }

    private fun renderSnackBar(errorResourceId: Int) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            requireContext().getString(errorResourceId),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun renderContent(album: AlbumUiModel) {
        albumImage.visibility = View.VISIBLE
        imageLoader.execute(
            LoadRequest.Builder(requireContext())
                .data(album.coverUrl)
                .target(albumImage)
                .crossfade(true)
                .crossfade(1000)
                .transformations(GrayscaleTransformation())
                .build()
        )

        albumName.text = album.name.also {
            toolbarLayout.title = it
        }
        bandName.text = album.artist
        albumDescription.text = album.wiki

        fab.visibility = View.VISIBLE

        album.tags?.forEach { tag ->
            val chip = layoutInflater.inflate(
                R.layout.chip_layout,
                tagsContainer,
                false
            ) as Chip

            chip.text = tag
            tagsContainer.addView(chip)
        }
    }
}
