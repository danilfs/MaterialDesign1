package com.example.materialdesign1.domain.model

interface INasaRepository {
    suspend fun requestPictureOfTheDay(daysAgo: Int = 0): PictureOfTheDay
}