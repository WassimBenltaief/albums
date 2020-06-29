package com.wassim.showcase.features.albums.list

import androidx.annotation.StringRes
import com.wassim.showcase.features.albums.AlbumUiModel

sealed class AlbumsUiState {
    data class Content(val list: List<AlbumUiModel>) : AlbumsUiState()
    data class Error(@StringRes val resId: Int) : AlbumsUiState()
    object Loading : AlbumsUiState()
}
