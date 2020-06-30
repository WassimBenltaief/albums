package com.wassim.showcase.features.albums.item.usecase

import com.wassim.showcase.data.local.AlbumsDao
import com.wassim.showcase.data.local.TagsDao
import com.wassim.showcase.data.local.model.asAlbumEntity
import com.wassim.showcase.utils.Result
import com.wassim.testutils.MainCoroutineRule
import com.wassim.testutils.album
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SaveAlbumUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    lateinit var saveAlbumUseCase: com.wassim.albums.view.item.usecase.SaveAlbumUseCase
    private val albumsDao: AlbumsDao = mockk()
    private val tagsDao: TagsDao = mockk()
    private val albumId: Long = 10
    private val stubAlbum = album(albumId.toString())

    @Before
    fun setUp() {
        saveAlbumUseCase =
            com.wassim.albums.view.item.usecase.SaveAlbumUseCaseImpl(
                albumsDao,
                tagsDao
            )
    }

    @Test
    fun `when saving album return success`() = runBlocking {
        // mock
        coEvery { albumsDao.insert(stubAlbum.asAlbumEntity()) } returns albumId
        coEvery { tagsDao.insertTagsForAlbum(any(), albumId) } returns listOf()
        // invoke
        val result = saveAlbumUseCase(stubAlbum)
        // assert
        assert(result is Result.Success<Long>)
        result as Result.Success
        assert(result.data == albumId)
    }

    @Test
    fun `when saving album return error`() = runBlocking {
        // mock
        val errorMessage = "unable to save album"
        coEvery { albumsDao.insert(stubAlbum.asAlbumEntity()) } throws Exception(errorMessage)
        coEvery { tagsDao.insertTagsForAlbum(any(), albumId) } returns listOf()
        // invoke
        val result = saveAlbumUseCase(stubAlbum)
        // assert
        assert(result is Result.Error)
        result as Result.Error
        assert(result.exception.message == errorMessage)
    }
}
