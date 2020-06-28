package com.wassim.showcase.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class AlbumsDao: BaseDao<AlbumEntity> {

    @Query("SELECT * FROM album")
    abstract suspend fun getAllAlbums(): List<AlbumEntity>

}