package com.wassim.showcase.data.local

import androidx.room.Dao
import androidx.room.Query
import com.wassim.showcase.data.local.model.TagEntity

@Dao
abstract class TagsDao : BaseDao<TagEntity> {

    @Query("SELECT * FROM tagEntity where ref_album_id=:albumId")
    abstract suspend fun findTagsForAlbum(albumId: Long): List<TagEntity>

    open fun insertTagsForAlbum(tags: List<TagEntity>, insertedAlbumId: Long) = tags
        .map { it.copy(refAlbumId = insertedAlbumId) }
        .apply { insert(this) }
}