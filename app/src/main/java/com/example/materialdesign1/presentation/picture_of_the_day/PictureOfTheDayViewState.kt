package com.example.materialdesign1.presentation.picture_of_the_day

import com.example.materialdesign1.domain.model.PictureOfTheDay

data class PictureOfTheDayViewState(
    val loading: Boolean = true,
    val pictureOfTheDay: PictureOfTheDay? = null,
    val error: Throwable? = null,
)