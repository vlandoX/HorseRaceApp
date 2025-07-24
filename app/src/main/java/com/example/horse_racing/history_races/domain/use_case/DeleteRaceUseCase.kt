package com.example.horse_racing.history_races.domain.use_case

import com.example.horse_racing.core.domain.model.RaceItem
import com.example.horse_racing.core.domain.repository.RaceRepository

class DeleteRaceUseCase (
    private val raceRepository: RaceRepository
) {

    suspend operator fun invoke(raceItem: RaceItem) {
        raceRepository.deleteRace(raceItem)
    }

}