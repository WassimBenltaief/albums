package com.wassim.showcase.featurealbums.item.usecase

import com.wassim.showcase.core.data.remote.Album
import com.wassim.showcase.core.data.remote.ApiService
import com.wassim.showcase.core.data.remote.GetAlbumInfoResponse
import com.wassim.showcase.core.utils.album
import com.wassim.showcase.featurealbums.view.item.usecase.GetAlbumInfoUseCase
import com.wassim.showcase.featurealbums.view.item.usecase.GetAlbumInfoUseCaseImpl
import com.wassim.showcase.testshared.MainCoroutineRule
import com.wassim.showcase.utils.Result
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

    lateinit var getAlbumInfoUseCase: GetAlbumInfoUseCase

    private val apiService: ApiService = mockk()
    private val albumId = "albumId"
    private val mockedSuccessResponse =
        GetAlbumInfoResponse(album(albumId))

    @Before
    fun setUp() {
        getAlbumInfoUseCase = GetAlbumInfoUseCaseImpl(apiService)
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
