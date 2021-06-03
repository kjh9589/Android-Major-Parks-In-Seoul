package com.teamnoyes.majorparksinseoul.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookmarkDatabaseDao {
    @Insert
    suspend fun insert(bookmark: Bookmark)

    @Query("DELETE FROM bookmark_table WHERE P_IDX = :key")
    suspend fun deleteBookmark(key: Int)

    //Room은 이미 LiveData를 반환하는 특정 Query에 대한 백그라운드 스레드를 사용한다.
    //따라서 suspend가 필요 없다.
    @Query("SELECT * FROM bookmark_table")
    fun getAllBookmark(): LiveData<List<Bookmark>>

    @Query("SELECT * FROM bookmark_table WHERE P_IDX = :key")
    suspend fun getBookmark(key: Int): Bookmark?
}