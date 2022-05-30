package com.example.materialdesign1.data

import com.example.materialdesign1.data.nasa_api.NasaApiService
import com.example.materialdesign1.data.nasa_api.mapper.PictureOfTheDayMapper
import com.example.materialdesign1.domain.model.INasaRepository
import com.example.materialdesign1.domain.model.PictureOfTheDay
import com.example.materialdesign1.utils.minusDays
import com.example.materialdesign1.utils.toQueryString
import java.util.*
import javax.inject.Inject

class NasaRepository @Inject constructor(
    private val nasaApiService: NasaApiService,
    private val pictureOfTheDayMapper: PictureOfTheDayMapper
) : INasaRepository {
    override suspend fun requestPictureOfTheDay(daysAgo: Int): PictureOfTheDay =
        pictureOfTheDayMapper.mapToDomain(nasaApiService.requestPictureOfTheDay(Date().minusDays(daysAgo).toQueryString()))
}