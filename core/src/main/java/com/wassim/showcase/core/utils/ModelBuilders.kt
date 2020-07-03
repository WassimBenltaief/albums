package com.wassim.showcase.core.utils

import com.wassim.showcase.core.data.remote.Album
import com.wassim.showcase.core.data.remote.AlbumCover
import com.wassim.showcase.core.data.remote.AlbumList
import com.wassim.showcase.core.data.remote.AlbumSearchWrapper
import com.wassim.showcase.core.data.remote.AlbumTag
import com.wassim.showcase.core.data.remote.AlbumWiki
import com.wassim.showcase.core.data.remote.ImageSize
import com.wassim.showcase.core.data.remote.SearchAlbumResponse

fun stubSearchResponse(numberOfAlbums: Int = 10) = SearchAlbumResponse(
    results = AlbumSearchWrapper(
        albumMatches = AlbumList(
            albums = (1..numberOfAlbums).map {
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
