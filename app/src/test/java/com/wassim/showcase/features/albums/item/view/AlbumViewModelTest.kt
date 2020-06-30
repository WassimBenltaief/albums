package com.wassim.showcase.features.albums.item.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.wassim.showcase.R
import com.wassim.albums.view.item.SingleAlbumUiState
import com.wassim.albums.view.item.usecase.GetAlbumInfoUseCase
import com.wassim.albums.view.item.usecase.SaveAlbumUseCase
import com.wassim.showcase.utils.Result
import com.wassim.testutils.MainCoroutineRule
import com.wassim.testutils.album
import io.mockk.CapturingSlot
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class AlbumViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: com.wassim.albums.view.item.view.AlbumViewModel

    private val getAlbumInfoUseCase: com.wassim.albums.view.item.usecase.GetAlbumInfoUseCase = mockk()
    private val saveAlbumUseCase: com.wassim.albums.view.item.usecase.SaveAlbumUseCase = mockk()
    private val albumId = "albumId"
    private val savedAlbumId: Long = 10
    private val mockedSuccessResponse = album(albumId)
    private val uiStateObserver: Observer<com.wassim.albums.view.item.SingleAlbumUiState> = spyk()

    @Test
    fun `find album returns success`() = runBlocking {
        // mock
        coEvery {
            getAlbumInfoUseCase(any(), any(), any())
        } returns Result.Success(mockedSuccessResponse)

        // observe
        viewModel = com.wassim.albums.view.item.view.AlbumViewModel(
            getAlbumInfoUseCase,
            saveAlbumUseCase
        )
        viewModel.uiState.observeForTesting(uiStateObserver) {
            viewModel.findAlbum(albumId, "", "")
            // verify
            val list: MutableList<com.wassim.albums.view.item.SingleAlbumUiState> = mutableListOf()
            coVerify(exactly = 1) { getAlbumInfoUseCase(albumId, any(), any()) }
            coVerify(exactly = 2) { it.onChanged(capture(list)) }
            // assert
            assert(list.first() is com.wassim.albums.view.item.SingleAlbumUiState.Loading)
            assert(list[1] is com.wassim.albums.view.item.SingleAlbumUiState.Content)
            val secondEmission = list[1] as com.wassim.albums.view.item.SingleAlbumUiState.Content
            assert(secondEmission.album.id == albumId)
        }
    }

    @Test
    fun `find album returns error`() = runBlocking {
        // mock
        val exception = Exception("unable to load album")
        coEvery {
            getAlbumInfoUseCase(any(), any(), any())
        } returns Result.Error(exception)

        // observe
        viewModel = com.wassim.albums.view.item.view.AlbumViewModel(
            getAlbumInfoUseCase,
            saveAlbumUseCase
        )
        viewModel.uiState.observeForTesting(uiStateObserver) {
            viewModel.findAlbum(albumId, "", "")
            // verify
            val list: MutableList<com.wassim.albums.view.item.SingleAlbumUiState> = mutableListOf()
            coVerify(exactly = 1) { getAlbumInfoUseCase(albumId, any(), any()) }
            coVerify(exactly = 2) { it.onChanged(capture(list)) }
            // assert
            assert(list.first() is com.wassim.albums.view.item.SingleAlbumUiState.Loading)
            assert(list[1] is com.wassim.albums.view.item.SingleAlbumUiState.SnackBar)
            val secondEmission = list[1] as com.wassim.albums.view.item.SingleAlbumUiState.SnackBar
            assert(secondEmission.resId == R.string.generic_single_album_error)
        }
    }

    @Test
    fun `save album returns success`() = runBlocking {
        // supposing get album returned success
        coEvery {
            getAlbumInfoUseCase(any(), any(), any())
        } returns Result.Success(mockedSuccessResponse)

        // mock save album result
        coEvery {
            saveAlbumUseCase(mockedSuccessResponse)
        } returns Result.Success(savedAlbumId)

        // observe
        viewModel = com.wassim.albums.view.item.view.AlbumViewModel(
            getAlbumInfoUseCase,
            saveAlbumUseCase
        )

        viewModel.findAlbum(albumId, "", "")

        viewModel.uiState.observeForTesting(uiStateObserver) {
            viewModel.markAsFavorite()
            // verify : we ignore all live data emissions and get the last one with "slot"
            val slot: CapturingSlot<com.wassim.albums.view.item.SingleAlbumUiState> = slot()
            coVerify(exactly = 1) { saveAlbumUseCase(mockedSuccessResponse) }
            coVerify(exactly = 2) { it.onChanged(capture(slot)) }
            // assert
            assert(slot.captured is com.wassim.albums.view.item.SingleAlbumUiState.SnackBar)
            val emission = slot.captured as com.wassim.albums.view.item.SingleAlbumUiState.SnackBar
            assert(emission.resId == R.string.marked_as_favorite)
        }
    }

    @Test
    fun `save album returns error`() = runBlocking {
        // supposing get album returned error
        coEvery {
            getAlbumInfoUseCase(any(), any(), any())
        } returns Result.Success(mockedSuccessResponse)

        // mock save album result return error
        val exception = Exception("unable to load album")
        coEvery {
            saveAlbumUseCase(mockedSuccessResponse)
        } returns Result.Error(exception)

        // observe
        viewModel = com.wassim.albums.view.item.view.AlbumViewModel(
            getAlbumInfoUseCase,
            saveAlbumUseCase
        )

        viewModel.findAlbum(albumId, "", "")

        viewModel.uiState.observeForTesting(uiStateObserver) {
            viewModel.markAsFavorite()
            // verify : we ignore all live data emissions and get the last one with "slot"
            val slot: CapturingSlot<com.wassim.albums.view.item.SingleAlbumUiState> = slot()
            coVerify(exactly = 1) { saveAlbumUseCase(mockedSuccessResponse) }
            coVerify(exactly = 2) { it.onChanged(capture(slot)) }
            // assert
            assert(slot.captured is com.wassim.albums.view.item.SingleAlbumUiState.SnackBar)
            val emission = slot.captured as com.wassim.albums.view.item.SingleAlbumUiState.SnackBar
            assert(emission.resId == R.string.unable_to_mark_album_as_favorite)
        }
    }
}
