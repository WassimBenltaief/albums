package com.wassim.showcase.featurealbums.item.usecase

import com.wassim.showcase.core.data.local.AlbumsDao
import com.wassim.showcase.core.data.local.TagsDao
import com.wassim.showcase.core.data.local.model.asAlbumEntity
import com.wassim.showcase.core.utils.album
import com.wassim.showcase.featurealbums.view.item.usecase.SaveAlbumUseCase
import com.wassim.showcase.featurealbums.view.item.usecase.SaveAlbumUseCaseImpl
import com.wassim.showcase.testshared.MainCoroutineRule
import com.wassim.showcase.utils.Result
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

    lateinit var saveAlbumUseCase: SaveAlbumUseCase
    private val albumsDao: AlbumsDao = mockk()
    private val tagsDao: TagsDao = mockk()
    private val albumId: Long = 10
    private val stubAlbum = album(albumId.toString())

    @Before
    fun setUp() {
        saveAlbumUseCase = SaveAlbumUseCaseImpl(
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
