package com.teamnoyes.majorparksinseoul

import android.app.Application
import com.teamnoyes.majorparksinseoul.database.BookmarkDatabase
import com.teamnoyes.majorparksinseoul.repository.ParkRepository
import com.teamnoyes.majorparksinseoul.util.NetworkLiveData

class ParkApplication: Application() {
    lateinit var parkRepository: ParkRepository
    private val database: BookmarkDatabase by lazy { BookmarkDatabase.getInstance(this) }
    override fun onCreate() {
        super.onCreate()
        parkRepository = ParkRepository(applicationContext, database.bookmarkDatabaseDao)
        NetworkLiveData.init(this)
    }
}