package com.teamnoyes.majorparksinseoul.model


import com.google.gson.annotations.SerializedName

data class SearchParkInfoService(
    @SerializedName("list_total_count")
    val listTotalCount: Int?,
    @SerializedName("RESULT")
    val rESULT: RESULT?,
    @SerializedName("row")
    val row: List<Row>?
)