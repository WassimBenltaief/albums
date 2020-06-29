package com.wassim.showcase.features.albums.list.usecase

import com.wassim.showcase.data.Album
import com.wassim.showcase.utils.Result

interface GetAlbumsUseCase {
    suspend operator fun invoke(): Result<List<Album>>
}
