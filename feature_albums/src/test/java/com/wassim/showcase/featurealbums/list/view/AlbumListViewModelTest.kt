package com.wassim.showcase.featurealbums.list.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.wassim.showcase.R
import com.wassim.showcase.core.utils.stubSearchResponse
import com.wassim.showcase.featurealbums.view.list.AlbumsUiState
import com.wassim.showcase.featurealbums.view.list.usecase.GetAlbumsUseCase
import com.wassim.showcase.featurealbums.view.list.view.AlbumListViewModel
import com.wassim.showcase.testshared.MainCoroutineRule
import com.wassim.showcase.testshared.observeForTesting
import com.wassim.showcase.utils.Result
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

    lateinit var viewModel: AlbumListViewModel

    private val getAlbumsUseCase: GetAlbumsUseCase = mockk()
    private val mockedSuccessResponse = stubSearchResponse().albums()
    private val uiStateObserver: Observer<AlbumsUiState> = spyk()

    @Test
    fun `search albums success returns uiState content`() = runBlocking {
        // mock
        coEvery {
            getAlbumsUseCase()
        } returns Result.Success(mockedSuccessResponse)

        // observe
        viewModel = AlbumListViewModel(getAlbumsUseCase)
        viewModel.uiState.observeForTesting(uiStateObserver) {
            viewModel.loadAlbums()
            // verify
            val list: MutableList<AlbumsUiState> = mutableListOf()
            coVerify(exactly = 1) { getAlbumsUseCase() }
            coVerify(exactly = 2) { it.onChanged(capture(list)) }
            // assert
            assert(list.first() is AlbumsUiState.Loading)
            assert(list[1] is AlbumsUiState.Content)
            val secondEmission = list[1] as AlbumsUiState.Content
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
        viewModel = AlbumListViewModel(getAlbumsUseCase)
        viewModel.uiState.observeForTesting(uiStateObserver) {
            viewModel.loadAlbums()
            // verify
            val list: MutableList<AlbumsUiState> = mutableListOf()
            coVerify(exactly = 1) { getAlbumsUseCase() }
            coVerify(exactly = 2) { it.onChanged(capture(list)) }
            // assert
            assert(list.first() is AlbumsUiState.Loading)
            assert(list[1] is AlbumsUiState.Error)
            val secondEmission = list[1] as AlbumsUiState.Error
            assert(secondEmission.resId == R.string.generic_albums_error)
        }
    }
}
