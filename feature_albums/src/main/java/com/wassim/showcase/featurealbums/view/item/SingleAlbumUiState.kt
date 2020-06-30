package com.wassim.showcase.featurealbums.view.item

import androidx.annotation.StringRes
import com.wassim.showcase.featurealbums.view.AlbumUiModel

sealed class SingleAlbumUiState {
    data class Content(val album: AlbumUiModel) : SingleAlbumUiState()
    data class SnackBar(@StringRes val resId: Int) : SingleAlbumUiState()
    object Loading : SingleAlbumUiState()
}
