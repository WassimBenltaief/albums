package com.wassim.showcase.features.favorite.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.wassim.showcase.R
import com.wassim.showcase.features.albums.list.AlbumsUiState
import com.wassim.showcase.features.favorite.usecase.GetAllFavoriteAlbumsUseCase
import com.wassim.showcase.utils.Result
import com.wassim.testutils.MainCoroutineRule
import com.wassim.testutils.observeForTesting
import com.wassim.testutils.stubSearchResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class FavoriteAlbumsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: FavoriteAlbumsViewModel

    private val getAllFavoriteAlbumsUseCase: GetAllFavoriteAlbumsUseCase = mockk()
    private val mockedSuccessResponse = stubSearchResponse(10).albums()
    private val uiStateObserver: Observer<AlbumsUiState> = spyk()

    @Test
    fun `when get all favorite albums then returns success`() = runBlocking {
        // mock
        coEvery {
            getAllFavoriteAlbumsUseCase()
        } returns Result.Success(mockedSuccessResponse)

        // observe
        viewModel = FavoriteAlbumsViewModel(getAllFavoriteAlbumsUseCase)
        viewModel.uiState.observeForTesting(uiStateObserver) {
            viewModel.loadFavoriteAlbums()
            // verify
            val list: MutableList<AlbumsUiState> = mutableListOf()
            coVerify(exactly = 1) { getAllFavoriteAlbumsUseCase() }
            coVerify(exactly = 2) { it.onChanged(capture(list)) }
            // assert
            assert(list.first() is AlbumsUiState.Loading)
            assert(list[1] is AlbumsUiState.Content)
            val secondEmission = list[1] as AlbumsUiState.Content
            assert(secondEmission.list.size == 10)
        }
    }

    @Test
    fun `when get all favorite albums then returns error`() = runBlocking {
        // mock
        val exception = Exception("error")
        coEvery {
            getAllFavoriteAlbumsUseCase()
        } returns Result.Error(exception)

        // observe
        viewModel = FavoriteAlbumsViewModel(getAllFavoriteAlbumsUseCase)
        viewModel.uiState.observeForTesting(uiStateObserver) {
            viewModel.loadFavoriteAlbums()
            // verify
            val list: MutableList<AlbumsUiState> = mutableListOf()
            coVerify(exactly = 1) { getAllFavoriteAlbumsUseCase() }
            coVerify(exactly = 2) { it.onChanged(capture(list)) }
            // assert
            assert(list.first() is AlbumsUiState.Loading)
            assert(list[1] is AlbumsUiState.Error)
            val secondEmission = list[1] as AlbumsUiState.Error
            assert(secondEmission.resId == R.string.generic_albums_error)
        }
    }
}
