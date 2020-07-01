package com.wassim.showcase.favorite

import androidx.annotation.StringRes

data class FavoriteAlbumsUiModel(
    val count: Int
)

sealed class FavoriteAlbumsUiState {
    data class Content(val data: FavoriteAlbumsUiModel) : FavoriteAlbumsUiState()
    data class Error(@StringRes val resId: Int) : FavoriteAlbumsUiState()
    object Loading : FavoriteAlbumsUiState()
}