package com.wassim.showcase.core.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "album",
    indices = [Index(
        value = [
            AlbumEntity.KEY_REMOTE_ID,
            AlbumEntity.KEY_NAME,
            AlbumEntity.KEY_ARTIST
        ],
        unique = true
    )
    ]
)
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = KEY_ALBUM_ID)
    val id: Long = 0,

    /**
     * the id used by the remote api
     */
    @ColumnInfo(name = KEY_REMOTE_ID)
    val remoteId: String? = null,

    @ColumnInfo(name = KEY_NAME)
    val name: String,

    @ColumnInfo(name = KEY_ARTIST)
    val artist: String,

    @ColumnInfo(name = KEY_IMAGE_URL)
    val imageUrl: String,

    @ColumnInfo(name = KEY_WIKI)
    val wiki: String? = null
) {
    companion object {
        const val KEY_ALBUM_ID = "album_id"
        const val KEY_REMOTE_ID = "remoteId"
        const val KEY_NAME = "name"
        const val KEY_ARTIST = "artist"
        const val KEY_IMAGE_URL = "imageUrl"
        const val KEY_WIKI = "wiki"
    }
}
