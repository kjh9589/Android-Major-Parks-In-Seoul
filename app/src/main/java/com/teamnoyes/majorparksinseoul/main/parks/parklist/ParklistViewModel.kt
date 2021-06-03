package com.teamnoyes.majorparksinseoul.main.parks.parklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamnoyes.majorparksinseoul.datamodel.ModelParklist
import com.teamnoyes.majorparksinseoul.utils.ParksData
import kotlinx.coroutines.launch

class ParklistViewModel(private val key: String) : ViewModel() {
    private val _loadData = MutableLiveData<List<ModelParklist>>()
    val loadData: LiveData<List<ModelParklist>>
        get() = _loadData

    fun getData(){
        viewModelScope.launch {
            val list = mutableListOf<ModelParklist>()

            for (i in ParksData.getData(key)){
                list.add(ModelParklist(i.P_IDX, i.P_PARK, i.GUIDANCE, i.P_ADDR, key))
            }

            _loadData.value = list
        }
    }
}