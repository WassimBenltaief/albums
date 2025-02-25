package com.wassim.showcase.core.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.wassim.showcase.core.data.remote.AlbumTag
import com.wassim.showcase.core.data.remote.TagsWrapper

data class AlbumWithTags(
    @Embedded val album: AlbumEntity,
    @Relation(
        parentColumn = AlbumEntity.KEY_ALBUM_ID,
        entityColumn = TagEntity.KEY_REFERENCE_ALBUM_ID
    )
    val tags: List<TagEntity>
) {
    fun mergeAlbumWithTags() = album.asAlbum().copy(
        tagsWrapper = TagsWrapper(tags = tags.map { tag -> AlbumTag(name = tag.name) })
    )
}
