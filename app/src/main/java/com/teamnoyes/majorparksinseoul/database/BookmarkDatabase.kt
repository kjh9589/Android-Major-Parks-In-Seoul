package com.teamnoyes.majorparksinseoul.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Bookmark::class], version = 1, exportSchema = false)
abstract class BookmarkDatabase: RoomDatabase() {

    abstract val bookmarkDatabaseDao: BookmarkDatabaseDao

    companion object{
        @Volatile //캐싱되지 않고 읽고 쓰기가 메인 메모리에서 진행됨
        private var INSTANCE: BookmarkDatabase? = null

        fun getInstance(context: Context): BookmarkDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookmarkDatabase::class.java,
                        "bookmark_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}