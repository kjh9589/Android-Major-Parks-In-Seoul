package com.teamnoyes.majorparksinseoul.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamnoyes.majorparksinseoul.repository.ParkRepository

class BookmarkViewModelFactory(private val repository: ParkRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java))
            return BookmarkViewModel(repository) as T
        throw IllegalArgumentException("UnKnown ViewModel Class")
    }
}