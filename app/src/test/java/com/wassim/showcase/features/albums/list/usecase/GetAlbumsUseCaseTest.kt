package com.wassim.showcase.features.albums.list.usecase

import com.wassim.testutils.MainCoroutineRule
import com.wassim.showcase.data.remote.ApiService
import com.wassim.testutils.stubSearchResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.wassim.showcase.utils.Result

class GetAlbumsUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    lateinit var getAlbumsUseCase: GetAlbumsUseCase

    private val apiService: ApiService = mockk()
    private val mockedSuccessResponse = stubSearchResponse()

    @Before
    fun setUp() {
        getAlbumsUseCase =
            GetAlbumsUseCaseImpl(
                apiService
            )
    }

    @Test
    fun `search albums returns success`() = runBlocking {
        // mock
        coEvery { apiService.searchAlbum(any(), any()) } returns mockedSuccessResponse
        // invoke
        val result = getAlbumsUseCase()
        // assert
        assert(result is Result.Success)
        result as Result.Success
        assert(result.data.size == 10)
    }

    @Test
    fun `search albums returns error`() = runBlocking {
        // mock
        val errorMessage = "server side error"
        coEvery { apiService.searchAlbum(any(), any()) } throws Exception(errorMessage)
        // invoke
        val result = getAlbumsUseCase()
        // assert
        assert(result is Result.Error)
        result as Result.Error
        assert(result.exception.message == errorMessage)
    }

}