package com.example.materialdesign1.domain.model

import com.example.materialdesign1.R


enum class PictureOfTheDayPreset(val title: Int, val daysAgo: Int) {
    TWO_DAYS_AGO(R.string.two_days_ago, 2),
    YESTERDAY(R.string.yesterday, 1),
    TODAY(R.string.today, 0),
}