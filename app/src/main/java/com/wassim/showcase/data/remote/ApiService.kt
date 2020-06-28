package com.wassim.showcase.data.remote

import com.wassim.showcase.data.GetAlbumInfoResponse
import com.wassim.showcase.data.SearchAlbumResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("./?method=album.search")
    suspend fun searchAlbum(
        @Query("album") phrase: String,
        @Query("limit") limit: Int
    ): SearchAlbumResponse

    @POST("./?method=album.getInfo")
    suspend fun getAlbumInfo(
        @Query("mbid") mbId: String,
        @Query("album") album: String?,
        @Query("artist") artist: String?
    ): GetAlbumInfoResponse
}