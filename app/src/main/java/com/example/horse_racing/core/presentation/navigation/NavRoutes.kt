package com.example.horse_racing.core.presentation.navigation

sealed class NavRoutes(val route: String) {
    object RaceScreen : NavRoutes("race")
    object HistoryRacesScreen : NavRoutes("history")
}