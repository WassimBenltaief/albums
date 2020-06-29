package com.wassim.showcase.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

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
