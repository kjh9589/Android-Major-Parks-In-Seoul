package com.teamnoyes.majorparksinseoul.network

import com.teamnoyes.majorparksinseoul.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ParkApiService {
    private const val baseUrl = "http://openAPI.seoul.go.kr:8088/"

    val retrofit: Service = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(buildOkHttpClient())
        .build()
        .create(Service::class.java)

    private fun buildOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    }else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            ).build()
}