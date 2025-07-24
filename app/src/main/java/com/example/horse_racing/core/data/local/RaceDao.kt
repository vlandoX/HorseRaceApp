package com.example.horse_racing.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RaceDao {
    
    @Insert
    suspend fun insertRaceEntity(raceEntity: RaceEntity)

    @Query("SELECT * FROM RaceEntity")
    suspend fun getAllRaceEntities(): List<RaceEntity>

    @Delete
    suspend fun deleteRaceEntity(raceEntity: RaceEntity)
}