package com.teamnoyes.majorparksinseoul.network

import com.teamnoyes.majorparksinseoul.datamodel.ModelAllParksData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeoulParkService {
    @GET("{START_INDEX}/{END_INDEX}")
    suspend fun getAllParkData(@Path("START_INDEX") START_INDEX: Int,
                               @Path("END_INDEX") END_INDEX: Int): Response<ModelAllParksData>
}