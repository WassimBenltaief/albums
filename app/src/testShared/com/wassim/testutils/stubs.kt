package com.wassim.testutils

import com.wassim.showcase.data.*

fun stubSearchResponse() = SearchAlbumResponse(
    results = AlbumSearchWrapper(
        albumMatches = AlbumList(
            albums = (1..10).map {
                album(it.toString())
            }
        )
    )
)

fun album(index: String = "0"): Album {
    return Album(
        id = index,
        name = "album $index",
        artist = "artist $index",
        images = listOf(
            AlbumCover(
                imageUrl = "url $index",
                imageSize = ImageSize.LARGE
            )
        ),
        wiki = AlbumWiki(summary = "summary $index")
    )
}

fun tag(index: String = "0") = AlbumTag(
    name = "tag $index"
)