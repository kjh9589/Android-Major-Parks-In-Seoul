package com.teamnoyes.majorparksinseoul.repository

import android.content.Context
import com.teamnoyes.majorparksinseoul.database.Bookmark
import com.teamnoyes.majorparksinseoul.database.BookmarkDatabaseDao
import com.teamnoyes.majorparksinseoul.model.ModelPark
import com.teamnoyes.majorparksinseoul.model.Row
import com.teamnoyes.majorparksinseoul.network.ParkApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class ParkRepository(private val applicationContext: Context, private val dbDAO: BookmarkDatabaseDao) {
    companion object {
        const val START_IDX = 1
        const val END_IDX = 1000
    }

    suspend fun getParksData() = withContext(Dispatchers.IO) {
        ParkApiService.retrofit.getAllParkData(START_IDX, END_IDX).body()
    }

    suspend fun getZone() = withContext(Dispatchers.IO) {
        val inputStream = applicationContext.assets.open("json/Region.json")
        val json = inputStream.bufferedReader().use {
            it.readText()
        }

        inputStream.close()
        JSONObject(json)
    }

    fun getAllBookmark() = dbDAO.getAllBookmark()

    suspend fun getBookmark(pIdx: Int) = withContext(Dispatchers.IO) {
        dbDAO.getBookmark(pIdx)
    }

    suspend fun deleteBookmark(pIdx: Int) = withContext(Dispatchers.IO) {
        dbDAO.deleteBookmark(pIdx)
    }

    suspend fun insertBookmark(bookmark: Bookmark) = withContext(Dispatchers.IO) {
        dbDAO.insert(bookmark)
    }
}