package com.teamnoyes.majorparksinseoul.detail_park

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamnoyes.majorparksinseoul.repository.ParkRepository

class DetailParkViewModelFactory(private val repository: ParkRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailParkViewModel::class.java))
            return DetailParkViewModel(repository) as T
        throw IllegalArgumentException("UnKnown ViewModel Class")
    }
}