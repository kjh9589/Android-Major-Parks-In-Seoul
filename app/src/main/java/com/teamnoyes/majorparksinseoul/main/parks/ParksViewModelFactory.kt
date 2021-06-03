package com.teamnoyes.majorparksinseoul.main.parks

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ParksViewModelFactory(private val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParksViewModel::class.java))
            return ParksViewModel(application) as T
        throw IllegalArgumentException("UnKnown ViewModel Class")
    }
}