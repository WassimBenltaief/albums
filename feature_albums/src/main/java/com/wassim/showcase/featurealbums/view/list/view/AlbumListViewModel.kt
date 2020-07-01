package com.wassim.showcase.featurealbums.view.list.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wassim.showcase.R
import com.wassim.showcase.featurealbums.view.list.AlbumsUiState
import com.wassim.showcase.featurealbums.view.list.usecase.GetAlbumsUseCase
import com.wassim.showcase.featurealbums.view.toUiModel
import com.wassim.showcase.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.launch

class AlbumListViewModel @Inject constructor(
    val getAlbums: GetAlbumsUseCase
) : ViewModel() {

    private val _state = MutableLiveData<AlbumsUiState>(AlbumsUiState.Loading)

    val uiState: LiveData<AlbumsUiState>
        get() = _state

    fun loadAlbums() = viewModelScope.launch {
        when (val result = getAlbums()) {
            is Result.Success -> {
                val uiModel = result.data.map {
                    it.toUiModel()
                }
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
