package com.teamnoyes.majorparksinseoul

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamnoyes.majorparksinseoul.repository.ParkRepository
import java.lang.IllegalArgumentException

class SharedViewModelFactory(private val repository: ParkRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java))
            return SharedViewModel(repository) as T
        throw IllegalArgumentException("UnKnown ViewModel Class")
    }
}