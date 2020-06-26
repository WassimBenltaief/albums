package com.wassim.showcase.features.albums.item.usecase

import com.wassim.showcase.data.ApiService
import com.wassim.showcase.utils.safeApiCall
import kotlinx.coroutines.Dispatchers

class GetAlbumInfoUseCaseImpl(
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