package com.teamnoyes.majorparksinseoul.zone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamnoyes.majorparksinseoul.repository.ParkRepository

class ZoneViewModelFactory(
    private val repository: ParkRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ZoneViewModel::class.java))
            return ZoneViewModel(repository) as T
        throw IllegalArgumentException("UnKnown ViewModel Class")
    }
}