package com.example.materialdesign1.utils
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

fun Date.toQueryString(): String = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(this)

fun Date.minusDays(daysCount: Int): Date = Date(time - 24*60*60*1000*daysCount)