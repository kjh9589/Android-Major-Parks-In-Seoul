package com.teamnoyes.majorparksinseoul.main.bookmark

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamnoyes.majorparksinseoul.database.BookmarkDatabaseDao
import java.lang.IllegalArgumentException

class BookmarkViewModelFactory(
    private val dataSource: BookmarkDatabaseDao,
    private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java))
            return BookmarkViewModel(dataSource, application) as T
        throw IllegalArgumentException("UnKnown ViewModel class")
    }
}