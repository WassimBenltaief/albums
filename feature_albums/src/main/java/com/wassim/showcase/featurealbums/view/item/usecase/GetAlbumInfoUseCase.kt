package com.wassim.showcase.featurealbums.view.item.usecase

import com.wassim.showcase.core.data.remote.Album
import com.wassim.showcase.utils.Result

interface GetAlbumInfoUseCase {
    suspend operator fun invoke(
        albumId: String,
        album: String,
        artist: String
    ): Result<Album>
}
