package com.example.horse_racing.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RaceEntity::class], version = 1)
abstract class RaceDb: RoomDatabase(){
    abstract val raceDao: RaceDao
}