package com.teamnoyes.majorparksinseoul.main.parks.parklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ParkslistViewModelFactory(private val key: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParklistViewModel::class.java))
            return ParklistViewModel(key) as T
        throw IllegalArgumentException("UnKnown ViewModel Class")
    }
}