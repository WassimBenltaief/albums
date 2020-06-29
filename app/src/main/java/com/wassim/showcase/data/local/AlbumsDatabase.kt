package com.wassim.showcase.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wassim.showcase.data.local.model.AlbumEntity
import com.wassim.showcase.data.local.model.TagEntity

@Database(
    entities = [
        AlbumEntity::class,
        TagEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AlbumsDatabase : RoomDatabase() {

    abstract fun albumsDao(): AlbumsDao

    abstract fun tagsDao(): TagsDao

    companion object {

        private const val DATABASE_NAME = "albums-db"

        @Volatile
        private var instance: AlbumsDatabase? = null

        fun getInstance(context: Context): AlbumsDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AlbumsDatabase {
            return Room.databaseBuilder(
                context, AlbumsDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}
