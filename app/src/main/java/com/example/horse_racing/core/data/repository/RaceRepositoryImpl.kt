package com.example.horse_racing.core.data.repository

import com.example.horse_racing.core.data.local.RaceDao
import com.example.horse_racing.core.data.mapppers.toNoteEntityForDelete
import com.example.horse_racing.core.data.mapppers.toNoteEntityForInsert
import com.example.horse_racing.core.data.mapppers.toNoteItem
import com.example.horse_racing.core.domain.model.RaceItem
import com.example.horse_racing.core.domain.repository.RaceRepository
import javax.inject.Inject

class RaceRepositoryImpl @Inject constructor(
    private val raceDao: RaceDao
) : RaceRepository {

    override suspend fun insertRace(raceItem: RaceItem) {
        raceDao.insertRaceEntity(raceItem.toNoteEntityForInsert())
    }

    override suspend fun deleteRace(raceItem: RaceItem) {
        raceDao.deleteRaceEntity(raceItem.toNoteEntityForDelete())
    }

    override suspend fun getAllRaces(): List<RaceItem> {
        return raceDao.getAllRaceEntities().map { it.toNoteItem() }
    }
}