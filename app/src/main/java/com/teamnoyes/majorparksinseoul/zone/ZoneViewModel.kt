package com.teamnoyes.majorparksinseoul.zone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnoyes.majorparksinseoul.model.ModelZone
import com.teamnoyes.majorparksinseoul.repository.ParkRepository
import kotlinx.coroutines.launch

class ZoneViewModel(private val repository: ParkRepository) : ViewModel() {
    private val _loadZone = MutableLiveData<List<ModelZone>>()
    val loadZone: LiveData<List<ModelZone>>
        get() = _loadZone

    fun getZone() {
        viewModelScope.launch {
            try {
                val jsonObject = repository.getZone()
                val list = mutableListOf<ModelZone>()
                for (key in jsonObject.keys()) {
                    list.add(ModelZone(jsonObject[key].toString(), key))
                }
                _loadZone.value = list
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



    override fun onCleared() {
        println("zoneViewModel onCleared")
        super.onCleared()
    }
}