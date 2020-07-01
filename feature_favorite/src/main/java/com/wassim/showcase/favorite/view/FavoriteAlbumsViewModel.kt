package com.wassim.showcase.favorite.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wassim.showcase.R
import com.wassim.showcase.favorite.FavoriteAlbumsUiModel
import com.wassim.showcase.favorite.FavoriteAlbumsUiState
import com.wassim.showcase.favorite.usecase.GetAllFavoriteAlbumsUseCase
import com.wassim.showcase.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteAlbumsViewModel @Inject constructor(
    val getAllFavoriteAlbums: GetAllFavoriteAlbumsUseCase
) : ViewModel() {

    private val _state = MutableLiveData<FavoriteAlbumsUiState>(
        FavoriteAlbumsUiState.Loading
    )
    val uiState: LiveData<FavoriteAlbumsUiState>
        get() = _state

    fun loadFavoriteAlbums() = viewModelScope.launch {
        when (val result = getAllFavoriteAlbums()) {
            is Result.Success -> {
                _state.value =
                    FavoriteAlbumsUiState.Content(
                        data = FavoriteAlbumsUiModel(
                            result.data.count()
                        )
                    )
            }
            is Result.Error -> _state.value =
                FavoriteAlbumsUiState.Error(
                    resId = R.string.generic_albums_error
                )
        }
    }
}
