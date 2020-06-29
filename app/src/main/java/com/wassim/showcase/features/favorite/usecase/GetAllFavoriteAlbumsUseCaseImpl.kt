package com.wassim.showcase.features.favorite.usecase

import com.wassim.showcase.data.local.AlbumsDao
import com.wassim.showcase.utils.safeApiCall
import kotlinx.coroutines.Dispatchers

class GetAllFavoriteAlbumsUseCaseImpl(
    private val albumsDao: AlbumsDao
) : GetAllFavoriteAlbumsUseCase {
    override suspend fun invoke() = safeApiCall(Dispatchers.IO) {
        albumsDao.getAllAlbumsWithTags().map { it.mergeAlbumWithTags() }
    }
}