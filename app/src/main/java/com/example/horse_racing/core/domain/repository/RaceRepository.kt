package com.example.horse_racing.core.domain.repository

import com.example.horse_racing.core.domain.model.RaceItem

interface RaceRepository {

    suspend fun insertRace(raceItem: RaceItem)

    suspend fun deleteRace(raceItem: RaceItem)

    suspend fun getAllRaces(): List<RaceItem>
}