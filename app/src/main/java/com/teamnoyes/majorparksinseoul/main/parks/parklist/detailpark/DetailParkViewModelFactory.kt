package com.teamnoyes.majorparksinseoul.main.parks.parklist.detailpark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamnoyes.majorparksinseoul.database.BookmarkDatabaseDao
import java.lang.IllegalArgumentException

class DetailParkViewModelFactory(private val regionName: String, private val pIdx: Int,
                                 private val dataSource: BookmarkDatabaseDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailParkViewModel::class.java))
            return DetailParkViewModel(regionName, pIdx, dataSource) as T
        throw IllegalArgumentException("UnKnown ViewModel Class")
    }
}