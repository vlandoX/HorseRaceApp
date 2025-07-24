package com.example.horse_racing.race.domain.use_case

import com.example.horse_racing.core.domain.model.RaceItem
import com.example.horse_racing.core.domain.repository.RaceRepository

class InsertRaceUseCase(
    private val raceRepository: RaceRepository
) {
    suspend operator fun invoke(
        winner: Int?,
    ): Boolean {
        if(winner == null) return false
        val race = RaceItem(
            winner = winner,
            dateRace = System.currentTimeMillis(),

        )
        raceRepository.insertRace(race)
        return true
    }
}