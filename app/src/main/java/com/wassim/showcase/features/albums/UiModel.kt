package com.wassim.showcase.features.albums

import com.wassim.showcase.data.Album
import com.wassim.showcase.data.ImageSize

data class AlbumUiModel(
    val id: String = "",
    val name: String,
    val artist: String,
    val coverUrl: String,
    val wiki: String? = null,
    val tags: List<String>? = null
)

fun Album.toUiModel() =
    AlbumUiModel(
        id = this.id ?: "",
        name = this.name,
        artist = this.artist,
        coverUrl = this.images.firstOrNull { it.imageSize == ImageSize.EXTRA_LARGE }?.imageUrl
            ?: "",
        wiki = this.wiki?.summary,
        tags = this.tagsWrapper?.tags?.take(6)?.map { it.name }
    )