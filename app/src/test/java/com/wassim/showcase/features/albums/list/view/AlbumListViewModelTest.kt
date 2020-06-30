package com.wassim.showcase.features.albums.list.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.wassim.showcase.R
import com.wassim.albums.view.list.AlbumsUiState
import com.wassim.albums.view.list.usecase.GetAlbumsUseCase
import com.wassim.showcase.utils.Result
import com.wassim.testutils.MainCoroutineRule
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

class AlbumListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: com.wassim.albums.view.list.view.AlbumListViewModel

    private val getAlbumsUseCase: com.wassim.albums.view.list.usecase.GetAlbumsUseCase = mockk()
    private val mockedSuccessResponse = stubSearchResponse().albums()
    private val uiStateObserver: Observer<com.wassim.albums.view.list.AlbumsUiState> = spyk()

    @Test
    fun `search albums success returns uiState content`() = runBlocking {
        // mock
        coEvery {
            getAlbumsUseCase()
        } returns Result.Success(mockedSuccessResponse)

        // observe
        viewModel =
            com.wassim.albums.view.list.view.AlbumListViewModel(
                getAlbumsUseCase
            )
        viewModel.uiState.observeForTesting(uiStateObserver) {
            viewModel.loadAlbums()
            // verify
            val list: MutableList<com.wassim.albums.view.list.AlbumsUiState> = mutableListOf()
            coVerify(exactly = 1) { getAlbumsUseCase() }
            coVerify(exactly = 2) { it.onChanged(capture(list)) }
            // assert
            assert(list.first() is com.wassim.albums.view.list.AlbumsUiState.Loading)
            assert(list[1] is com.wassim.albums.view.list.AlbumsUiState.Content)
            val secondEmission = list[1] as com.wassim.albums.view.list.AlbumsUiState.Content
            assert(secondEmission.list.size == 10)
        }
    }

    @Test
    fun `search albums fail returns uiState error`() = runBlocking {
        // mock
        val exception = Exception("error")
        coEvery {
            getAlbumsUseCase()
        } returns Result.Error(exception)

        // observe
        viewModel =
            com.wassim.albums.view.list.view.AlbumListViewModel(
                getAlbumsUseCase
            )
        viewModel.uiState.observeForTesting(uiStateObserver) {
            viewModel.loadAlbums()
            // verify
            val list: MutableList<com.wassim.albums.view.list.AlbumsUiState> = mutableListOf()
            coVerify(exactly = 1) { getAlbumsUseCase() }
            coVerify(exactly = 2) { it.onChanged(capture(list)) }
            // assert
            assert(list.first() is com.wassim.albums.view.list.AlbumsUiState.Loading)
            assert(list[1] is com.wassim.albums.view.list.AlbumsUiState.Error)
            val secondEmission = list[1] as com.wassim.albums.view.list.AlbumsUiState.Error
            assert(secondEmission.resId == R.string.generic_albums_error)
        }
    }
}
