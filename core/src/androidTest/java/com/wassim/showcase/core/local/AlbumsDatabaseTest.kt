package com.wassim.showcase.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.wassim.showcase.core.utils.album
import com.wassim.showcase.core.utils.tag
import java.io.IOException
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AlbumsDatabaseTest {

    private lateinit var albumsDao: AlbumsDao
    private lateinit var tagsDao: TagsDao
    private lateinit var db: AlbumsDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AlbumsDatabase::class.java
        ).build()
        albumsDao = db.albumsDao()
        tagsDao = db.tagsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insert_an_album_with_tags_and_read_it_from_database() = runBlocking {
        val albumEntity = album("one").asAlbumEntity()
        val albumId = albumsDao.insert(albumEntity)

        val tags = (1..10).map { tag(it.toString()).asTagEntity() }
        tagsDao.insertTagsForAlbum(tags, albumId)

        val insertedAlbum = albumsDao.getAllAlbums()
        assertEquals(1, insertedAlbum.size)
        assertEquals("artist one", insertedAlbum.first().artist)
        assertEquals("album one", insertedAlbum.first().name)

        val insertedTags = tagsDao.findTagsForAlbum(albumId)
        assertEquals(10, insertedTags.size)
        assertEquals("tag 10", insertedTags.last().name)
        assertEquals(albumId, insertedTags.last().refAlbumId)
    }

    @Test
    @Throws(Exception::class)
    fun insert_and_select_album_with_tags_model() = runBlocking {
        val albumEntity = album("one").asAlbumEntity()
        val albumId = albumsDao.insert(albumEntity)

        val tags = (1..10).map { tag(it.toString()).asTagEntity() }
        tagsDao.insertTagsForAlbum(tags, albumId)

        val insertedAlbums = albumsDao.getAllAlbumsWithTags()
        assertEquals(1, insertedAlbums.size)
        assertEquals(10, insertedAlbums.first().tags.size)
        assertEquals("tag 10", insertedAlbums.first().tags.last().name)
    }
}
