package com.wassim.showcase.featurealbums.view

import com.wassim.showcase.data.Album
import com.wassim.showcase.data.AlbumCover
import com.wassim.showcase.data.AlbumTag
import com.wassim.showcase.data.AlbumWiki
import com.wassim.showcase.data.ImageSize
import com.wassim.showcase.data.TagsWrapper

data class AlbumUiModel(
    val id: String = "",
    val name: String,
    val artist: String,
    val coverUrl: String,
    val wiki: String? = null,
    val tags: List<String>? = null,
    val markedAsFavorite: Boolean = false
)

fun Album.toUiModel() = AlbumUiModel(
    id = this.id ?: "",
    name = this.name,
    artist = this.artist,
    coverUrl = this.images.firstOrNull { it.imageSize == ImageSize.EXTRA_LARGE }?.imageUrl
        ?: "",
    wiki = this.wiki?.summary,
    tags = this.tagsWrapper?.tags?.take(6)?.map { it.name }
)

fun AlbumUiModel.toAlbum() = Album(
    id = this.id,
    name = this.name,
    artist = this.artist,
    images = listOf(AlbumCover(imageUrl = this.coverUrl, imageSize = ImageSize.EXTRA_LARGE)),
    wiki = this.wiki?.let { AlbumWiki(summary = it) },
    tagsWrapper = this.tags?.let { tag -> TagsWrapper(tags = tag.map { AlbumTag(name = it) }) }
)
