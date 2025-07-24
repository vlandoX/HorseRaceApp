package com.example.horse_racing.core.data.mapppers

import com.example.horse_racing.core.data.local.RaceEntity
import com.example.horse_racing.core.domain.model.RaceItem

fun RaceEntity.toNoteItem(): RaceItem {
    return RaceItem(
        id = id ?: 0,
        winner = winner,
        dateRace = dateRace
    )
}

fun RaceItem.toNoteEntityForInsert(): RaceEntity {
    return RaceEntity(
        winner = winner,
        dateRace = dateRace
    )
}

fun RaceItem.toNoteEntityForDelete(): RaceEntity {
    return RaceEntity(
        id = id,
        winner = winner,
        dateRace = dateRace
    )
}