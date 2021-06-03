package com.teamnoyes.majorparksinseoul.utils

import com.teamnoyes.majorparksinseoul.datamodel.ModelAllParkDataInfo

object ParksData {
    private lateinit var data: HashMap<String, List<ModelAllParkDataInfo>>

    fun setData(data: HashMap<String, List<ModelAllParkDataInfo>>){
        this.data = data
    }

    fun getData(key: String): List<ModelAllParkDataInfo>{
        return data[key]!!
    }
}