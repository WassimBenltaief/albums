package com.wassim.showcase.features.favorite.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wassim.showcase.R
import com.wassim.showcase.features.albums.list.AlbumsUiState
import com.wassim.showcase.features.albums.toUiModel
import com.wassim.showcase.features.favorite.usecase.GetAllFavoriteAlbumsUseCase
import com.wassim.showcase.utils.Result
import kotlinx.coroutines.launch

class FavoriteAlbumsViewModel(
    val getAllFavoriteAlbums: GetAllFavoriteAlbumsUseCase
) : ViewModel() {

    private val _state = MutableLiveData<AlbumsUiState>(
        AlbumsUiState.Loading
    )
    val uiState: LiveData<AlbumsUiState>
        get() = _state

    fun loadFavoriteAlbums() = viewModelScope.launch {
        when (val result = getAllFavoriteAlbums()) {
            is Result.Success -> {
                val uiModel = result.data.map { it.toUiModel() }
                _state.value =
                    AlbumsUiState.Content(
                        list = uiModel
                    )
            }
            is Result.Error -> _state.value =
                AlbumsUiState.Error(
                    resId = R.string.generic_albums_error
                )
        }
    }
}
