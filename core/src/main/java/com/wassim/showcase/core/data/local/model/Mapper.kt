package com.wassim.showcase.core.data.local.model

import com.wassim.showcase.core.data.remote.Album
import com.wassim.showcase.core.data.remote.AlbumCover
import com.wassim.showcase.core.data.remote.AlbumTag
import com.wassim.showcase.core.data.remote.AlbumWiki
import com.wassim.showcase.core.data.remote.ImageSize

fun Album.asAlbumEntity() =
    AlbumEntity(
        remoteId = this.id,
        name = this.name,
        artist = this.artist,
        imageUrl = findBiggestImageUrlOrFirst(),
        wiki = this.wiki?.summary
    )

fun AlbumEntity.asAlbum() = Album(
    id = this.remoteId,
    name = this.name,
    artist = this.artist,
    images = listOf(AlbumCover(imageUrl = this.imageUrl, imageSize = ImageSize.EXTRA_LARGE)),
    wiki = this.wiki?.let { AlbumWiki(summary = it) }
)

private fun Album.findBiggestImageUrlOrFirst() =
    this.images.firstOrNull { it.imageSize == ImageSize.EXTRA_LARGE }?.imageUrl
        ?: this.images.first().imageUrl

fun AlbumTag.asTagEntity() =
    TagEntity(name = this.name)
