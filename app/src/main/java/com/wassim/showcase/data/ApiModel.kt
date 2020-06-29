package com.wassim.showcase.data

import com.google.gson.annotations.SerializedName

data class SearchAlbumResponse(val results: AlbumSearchWrapper) {
    fun albums() = this.results.albumMatches.albums
}

data class AlbumSearchWrapper(
    @field:SerializedName("albummatches") val albumMatches: AlbumList
)
data class AlbumList(
    @field:SerializedName("album") val albums: List<Album>
)

data class GetAlbumInfoResponse(
    val album: Album
)

data class Album(
    @field:SerializedName("mbid")
    val id: String? = null,
    val name: String,
    val artist: String,
    @field:SerializedName("image")
    val images: List<AlbumCover>,
    @field:SerializedName("tags")
    val tagsWrapper: TagsWrapper? = null,
    @field:SerializedName("wiki")
    val wiki: AlbumWiki? = null
)
data class TagsWrapper(
    @field:SerializedName("tag")
    val tags: List<AlbumTag>
)

data class AlbumTag(
    val name: String
)

data class AlbumWiki(
    val summary: String
)

data class AlbumCover(
    @field:SerializedName("#text") val imageUrl: String,
    @field:SerializedName("size")
    val imageSize: ImageSize
)

enum class ImageSize {
    @field:SerializedName("small")
    SMALL,

    @field:SerializedName("medium")
    MEDIUM,

    @field:SerializedName("large")
    LARGE,

    @field:SerializedName("extralarge")
    EXTRA_LARGE,
}
