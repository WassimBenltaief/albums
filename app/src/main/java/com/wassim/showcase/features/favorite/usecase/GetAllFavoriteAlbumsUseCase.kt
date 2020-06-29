package com.wassim.showcase.features.favorite.usecase

import com.wassim.showcase.data.Album
import com.wassim.showcase.utils.Result

interface GetAllFavoriteAlbumsUseCase {
    suspend operator fun invoke(): Result<List<Album>>
}
