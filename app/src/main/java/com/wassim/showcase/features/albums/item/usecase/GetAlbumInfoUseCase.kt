package com.wassim.showcase.features.albums.item.usecase

import com.wassim.showcase.data.Album
import com.wassim.showcase.utils.Result

interface GetAlbumInfoUseCase {
    suspend operator fun invoke(
        albumId: String,
        album: String,
        artist: String
    ): Result<Album>
}
