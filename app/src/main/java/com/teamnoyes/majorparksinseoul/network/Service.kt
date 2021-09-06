package com.teamnoyes.majorparksinseoul.network

import com.teamnoyes.majorparksinseoul.BuildConfig
import com.teamnoyes.majorparksinseoul.model.ParkInfoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("${BuildConfig.SEOUL_API_KEY}/json/SearchParkInfoService/{START_INDEX}/{END_INDEX}")
    suspend fun getAllParkData(@Path("START_INDEX") START_INDEX: Int,
                               @Path("END_INDEX") END_INDEX: Int): Response<ParkInfoDTO>
}