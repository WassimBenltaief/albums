package com.wassim.showcase.featurealbums.view.list

import androidx.annotation.StringRes
import com.wassim.showcase.featurealbums.view.AlbumUiModel

sealed class AlbumsUiState {
    data class Content(val list: List<AlbumUiModel>) : AlbumsUiState()
    data class Error(@StringRes val resId: Int) : AlbumsUiState()
    object Loading : AlbumsUiState()
}
