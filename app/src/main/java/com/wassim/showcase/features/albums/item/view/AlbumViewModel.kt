package com.wassim.showcase.features.albums.item.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wassim.showcase.features.albums.item.SingleAlbumUiState
import com.wassim.showcase.R
import com.wassim.showcase.features.albums.item.usecase.GetAlbumInfoUseCase
import com.wassim.showcase.features.albums.toUiModel
import com.wassim.showcase.utils.Result
import kotlinx.coroutines.launch

class AlbumViewModel(
    val getAlbumInfo: GetAlbumInfoUseCase
) : ViewModel() {

    private val _state = MutableLiveData<SingleAlbumUiState>(
        SingleAlbumUiState.Loading
    )
    val uiState: LiveData<SingleAlbumUiState>
        get() = _state

    fun findAlbum(
        albumId: String,
        album: String,
        artist: String
    ) = viewModelScope.launch {
        when (val result = getAlbumInfo(albumId, album, artist)) {
            is Result.Success -> {
                _state.value = SingleAlbumUiState.Content(album = result.data.toUiModel())
            }
            is Result.Error -> {
                _state.value = SingleAlbumUiState.Error(
                    resId = R.string.generic_single_album_error
                )
            }
        }
    }
}