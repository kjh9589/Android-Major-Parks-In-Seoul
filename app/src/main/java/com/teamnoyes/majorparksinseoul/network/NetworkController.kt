package com.teamnoyes.majorparksinseoul.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.teamnoyes.majorparksinseoul.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkController {
    private val IP = "http://openAPI.seoul.go.kr:8088/"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val baseURL = IP + BuildConfig.SEOUL_API_KEY + "/json/SearchParkInfoService/"
    private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(baseURL).build()

    val seoulParkService: SeoulParkService by lazy { retrofit.create(SeoulParkService::class.java) }
}