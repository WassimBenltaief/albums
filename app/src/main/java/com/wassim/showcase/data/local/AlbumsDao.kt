package com.wassim.showcase.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.wassim.showcase.data.local.model.AlbumEntity
import com.wassim.showcase.data.local.model.AlbumWithTags

@Dao
abstract class AlbumsDao: BaseDao<AlbumEntity> {

    @Query("SELECT * FROM album")
    abstract suspend fun getAllAlbums(): List<AlbumEntity>

    @Transaction
    @Query("SELECT * FROM album")
    abstract suspend fun getAllAlbumsWithTags(): List<AlbumWithTags>

}