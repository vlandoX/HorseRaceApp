package com.example.horse_racing.core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.SportsScore

object NavBarItems {
    val barItems = listOf(
        BarItem(
            screen = NavRoutes.RaceScreen,
            icon = Icons.Default.SportsScore,
            label = "Скачки"
        ),
        BarItem(
            screen = NavRoutes.HistoryRacesScreen,
            icon = Icons.Default.History,
            label = "История"
        )
    )
}