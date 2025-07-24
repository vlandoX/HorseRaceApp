package com.example.horse_racing.race.domain.use_case

import javax.inject.Inject

class GenerateHorseSpeedsUseCase @Inject constructor() {
    operator fun invoke(horseCount:Int): List<Long>{
        return List(horseCount){ ((3000L..5000L).random())}
    }
}