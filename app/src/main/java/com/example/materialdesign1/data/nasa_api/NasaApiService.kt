package com.example.materialdesign1.data.nasa_api

import com.example.materialdesign1.data.model.ApiPictureOfTheDay
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {
    @GET("planetary/apod")
    suspend fun requestPictureOfTheDay(@Query("date") date: String): ApiPictureOfTheDay
}