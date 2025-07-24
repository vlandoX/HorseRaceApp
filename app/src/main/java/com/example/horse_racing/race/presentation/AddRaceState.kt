package com.example.horse_racing.race.presentation

data class AddRaceState(
    val winner: String = "",
)

enum class RaceState{
    IDLE, RUNNING, FINISHED
}
