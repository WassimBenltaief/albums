package com.wassim.showcase.features.albums.item.usecase

import com.wassim.showcase.data.Album
import com.wassim.showcase.data.GetAlbumInfoResponse
import com.wassim.showcase.data.remote.ApiService
import com.wassim.showcase.utils.Result
import com.wassim.testutils.MainCoroutineRule
import com.wassim.testutils.album
import io.mockk.coEvery
import io.mockk.mockk
import java.lang.Exception
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetAlbumInfoUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    lateinit var getAlbumInfoUseCase: com.wassim.albums.view.item.usecase.GetAlbumInfoUseCase

    private val apiService: ApiService = mockk()
    private val albumId = "albumId"
    private val mockedSuccessResponse =
        GetAlbumInfoResponse(album(albumId))

    @Before
    fun setUp() {
        getAlbumInfoUseCase =
            com.wassim.albums.view.item.usecase.GetAlbumInfoUseCaseImpl(
                apiService
            )
    }

    @Test
    fun `find album returns success`() = runBlocking {
        // mock
        coEvery { apiService.getAlbumInfo(albumId, any(), any()) } returns mockedSuccessResponse
        // invoke
        val result = getAlbumInfoUseCase(albumId, "", "")
        // assert
        assert(result is Result.Success<Album>)
        result as Result.Success
        assert(result.data.id == albumId)
    }

    @Test
    fun `find album returns error`() = runBlocking {
        // mock
        val message = "server side error"
        val error = Exception(message)
        coEvery { apiService.getAlbumInfo(albumId, any(), any()) } throws error
        // invoke
        val result = getAlbumInfoUseCase(albumId, "", "")
        // assert
        assert(result is Result.Error)
        result as Result.Error
        assert(result.exception.message == message)
    }
}
