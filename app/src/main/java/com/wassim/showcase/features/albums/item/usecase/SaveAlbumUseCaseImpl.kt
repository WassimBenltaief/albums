package com.wassim.showcase.features.albums.item.usecase

import com.wassim.showcase.data.Album
import com.wassim.showcase.data.local.AlbumsDao
import com.wassim.showcase.data.local.TagsDao
import com.wassim.showcase.data.local.model.asAlbumEntity
import com.wassim.showcase.data.local.model.asTagEntity
import com.wassim.showcase.utils.safeApiCall
import kotlinx.coroutines.Dispatchers

class SaveAlbumUseCaseImpl(
    private val albumsDao: AlbumsDao,
    private val tagsDao: TagsDao
) : SaveAlbumUseCase {
    override suspend fun invoke(album: Album) = safeApiCall(Dispatchers.IO) {
        val albumId = albumsDao.insert(album.asAlbumEntity())
        album.tagsWrapper?.tags?.map { it.asTagEntity() }
            ?.let {
                tagsDao.insertTagsForAlbum(it, albumId)
            }
        albumId
    }
}
