package com.example.materialdesign1.data.nasa_api.mapper

import com.example.materialdesign1.data.model.ApiPictureOfTheDay
import com.example.materialdesign1.domain.model.PictureOfTheDay
import javax.inject.Inject

class PictureOfTheDayMapper @Inject constructor() {
    fun mapToDomain(apiPictureOfTheDay: ApiPictureOfTheDay) = PictureOfTheDay(
        apiPictureOfTheDay.title,
        apiPictureOfTheDay.explanation,
        apiPictureOfTheDay.url
    )
}