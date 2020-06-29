package com.wassim.showcase.data.local.model

import com.wassim.showcase.data.*

fun Album.asAlbumEntity() = AlbumEntity(
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