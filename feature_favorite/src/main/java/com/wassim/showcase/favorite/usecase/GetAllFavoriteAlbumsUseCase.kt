package com.wassim.showcase.favorite.usecase

import com.wassim.showcase.core.data.remote.Album
import com.wassim.showcase.utils.Result

interface GetAllFavoriteAlbumsUseCase {
    suspend operator fun invoke(): Result<List<Album>>
}
