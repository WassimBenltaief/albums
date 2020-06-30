package com.wassim.showcase.featurealbums.view.item.usecase

import com.wassim.showcase.data.remote.ApiService
import com.wassim.showcase.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetAlbumInfoUseCaseImpl @Inject constructor(
    private val apiService: ApiService
) : GetAlbumInfoUseCase {

    override suspend fun invoke(
        albumId: String,
        album: String,
        artist: String
    ) = safeApiCall(Dispatchers.IO) {
        apiService.getAlbumInfo(
            mbId = albumId,
            album = album,
            artist = artist
        ).album
    }
}
