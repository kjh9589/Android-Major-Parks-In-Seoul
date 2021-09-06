package com.teamnoyes.majorparksinseoul.model


import com.google.gson.annotations.SerializedName

data class ParkInfoDTO(
    @SerializedName("SearchParkInfoService")
    val searchParkInfoService: SearchParkInfoService?
)