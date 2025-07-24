package com.example.horse_racing.history_races.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatTimeMillis(timeMillis: Long): String {
    val date = Date(timeMillis)
    val format = SimpleDateFormat("HH:mm dd.MM.yy", Locale.getDefault())
    return format.format(date)
}

