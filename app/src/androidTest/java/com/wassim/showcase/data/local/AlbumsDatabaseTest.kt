package com.wassim.showcase.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.wassim.testutils.album
import com.wassim.testutils.tag
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class SimpleEntityReadWriteTest {

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

        val tags = (0..9).map { tag(it.toString()).asTagEntity() }
        tagsDao.insertTagsForAlbum(tags, albumId)

        val insertedAlbum = albumsDao.getAllAlbums()
        assertEquals(1, insertedAlbum.size)
        assertEquals("artist one", insertedAlbum.first().artist)
        assertEquals("album one", insertedAlbum.first().name)

        val insertedTags = tagsDao.findTagsForAlbum(albumId)
        assertEquals(10, insertedTags.size)
        assertEquals("tag 9", insertedTags[9].name)
        assertEquals(albumId, insertedTags[9].refAlbumId)
    }
}