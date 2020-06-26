package com.wassim.showcase.view.item.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.wassim.showcase.features.albums.item.SingleAlbumUiState
import com.wassim.testutils.MainCoroutineRule
import com.wassim.testutils.observeForTesting
import com.wassim.testutils.stubOneAlbum
import com.wassim.showcase.R
import com.wassim.showcase.features.albums.item.usecase.GetAlbumInfoUseCase
import com.wassim.showcase.features.albums.item.view.AlbumViewModel
import com.wassim.showcase.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class AlbumViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AlbumViewModel

    private val getAlbumInfoUseCase: GetAlbumInfoUseCase = mockk()
    private val albumId = "albumId"
    private val mockedSuccessResponse = stubOneAlbum(albumId)
    private val uiStateObserver: Observer<SingleAlbumUiState> = spyk()

    @Test
    fun `find album returns success`() {
        // mock
        coEvery {
            getAlbumInfoUseCase(any(), any(), any())
        } returns Result.Success(mockedSuccessResponse)

        // observe
        viewModel =
            AlbumViewModel(
                getAlbumInfoUseCase
            )
        viewModel.uiState.observeForTesting(uiStateObserver) {
            viewModel.findAlbum(albumId, "", "")
            // verify
            val list: MutableList<SingleAlbumUiState> = mutableListOf()
            coVerify(exactly = 1) { getAlbumInfoUseCase(albumId, any(), any()) }
            coVerify(exactly = 2) { it.onChanged(capture(list)) }
            // assert
            assert(list.first() is SingleAlbumUiState.Loading)
            assert(list[1] is SingleAlbumUiState.Content)
            val secondEmission = list[1] as SingleAlbumUiState.Content
            assert(secondEmission.album.id == albumId)
        }
    }

    @Test
    fun `find album returns error`() {
        // mock
        val exception = Exception("unable to load album")
        coEvery {
            getAlbumInfoUseCase(any(), any(), any())
        } returns Result.Error(exception)

        // observe
        viewModel =
            AlbumViewModel(
                getAlbumInfoUseCase
            )
        viewModel.uiState.observeForTesting(uiStateObserver) {
            viewModel.findAlbum(albumId, "", "")
            // verify
            val list: MutableList<SingleAlbumUiState> = mutableListOf()
            coVerify(exactly = 1) { getAlbumInfoUseCase(albumId, any(), any()) }
            coVerify(exactly = 2) { it.onChanged(capture(list)) }
            // assert
            assert(list.first() is SingleAlbumUiState.Loading)
            assert(list[1] is SingleAlbumUiState.Error)
            val secondEmission = list[1] as SingleAlbumUiState.Error
            assert(secondEmission.resId == R.string.generic_single_album_error)
        }
    }
}