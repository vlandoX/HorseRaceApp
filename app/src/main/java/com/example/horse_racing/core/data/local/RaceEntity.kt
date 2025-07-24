package com.example.horse_racing.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RaceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var winner: Int, //Номер лошади
    var dateRace: Long,
)
