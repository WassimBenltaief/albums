package com.wassim.showcase.favorite.usecase

import com.wassim.showcase.core.data.local.AlbumsDao
import com.wassim.showcase.utils.safeApiCall
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

class GetAllFavoriteAlbumsUseCaseImpl @Inject constructor(
    private val albumsDao: AlbumsDao
) : GetAllFavoriteAlbumsUseCase {
    override suspend fun invoke() = safeApiCall(Dispatchers.IO) {
        albumsDao.getAllAlbumsWithTags().map { it.mergeAlbumWithTags() }
    }
}
