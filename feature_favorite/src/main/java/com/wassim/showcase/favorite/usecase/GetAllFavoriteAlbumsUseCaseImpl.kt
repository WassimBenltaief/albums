package com.wassim.showcase.favorite.usecase

import com.wassim.showcase.data.local.AlbumsDao
import com.wassim.showcase.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetAllFavoriteAlbumsUseCaseImpl @Inject constructor(
    private val albumsDao: AlbumsDao
) : GetAllFavoriteAlbumsUseCase {
    override suspend fun invoke() = safeApiCall(Dispatchers.IO) {
        albumsDao.getAllAlbumsWithTags().map { it.mergeAlbumWithTags() }
    }
}
