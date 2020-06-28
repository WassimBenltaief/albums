package com.wassim.showcase.data.local

import androidx.room.*
import com.wassim.showcase.data.Album
import com.wassim.showcase.data.AlbumTag
import com.wassim.showcase.data.ImageSize

@Entity(
    tableName = "album",
    indices = [
        Index(AlbumEntity.KEY_REMOTE_ID),
        Index(AlbumEntity.KEY_NAME),
        Index(AlbumEntity.KEY_ARTIST)
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

@Entity(
    tableName = "tagEntity",
    foreignKeys = [ForeignKey(
        entity = AlbumEntity::class,
        parentColumns = [AlbumEntity.KEY_ALBUM_ID],
        childColumns = [TagEntity.KEY_REFERENCE_ALBUM_ID],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index(TagEntity.KEY_NAME),
        Index(TagEntity.KEY_REFERENCE_ALBUM_ID)
    ]
)
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = KEY_TAG_ID)
    val id: Long = 0,

    @ColumnInfo(name = KEY_NAME)
    val name: String,

    @ColumnInfo(name = KEY_REFERENCE_ALBUM_ID)
    val refAlbumId: Long = 0
) {
    companion object {
        const val KEY_TAG_ID = "tag_id"
        const val KEY_NAME = "name"
        const val KEY_REFERENCE_ALBUM_ID = "ref_album_id"

    }
}

fun Album.asAlbumEntity() = AlbumEntity(
    remoteId = this.id,
    name = this.name,
    artist = this.artist,
    imageUrl = findBiggestImageUrlOrFirst(),
    wiki = this.wiki?.summary
)

private fun Album.findBiggestImageUrlOrFirst() =
    this.images.firstOrNull { it.imageSize == ImageSize.EXTRA_LARGE }?.imageUrl
        ?: this.images.first().imageUrl

fun AlbumTag.asTagEntity() = TagEntity(name = this.name)