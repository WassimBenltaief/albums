package com.wassim.showcase.features.albums.item

import androidx.annotation.StringRes
import com.wassim.showcase.features.albums.AlbumUiModel

sealed class SingleAlbumUiState {
    data class Content(val album: AlbumUiModel) : SingleAlbumUiState()
    data class SnackBar(@StringRes val resId: Int) : SingleAlbumUiState()
    object Loading : SingleAlbumUiState()
}