package com.example.horse_racing.race.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horse_racing.race.domain.use_case.GenerateHorseSpeedsUseCase
import com.example.horse_racing.race.domain.use_case.InsertRaceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RaceViewModel @Inject constructor(
    private val insertRaceUseCase: InsertRaceUseCase,
    private val generateHorseSpeedsUseCase: GenerateHorseSpeedsUseCase
) : ViewModel() {

    val horseNumbers = listOf(1, 2, 3)

    private val _raceState = MutableStateFlow(RaceState.IDLE)
    val raceState = _raceState.asStateFlow()

    private val _horseProgresses = MutableStateFlow(List(horseNumbers.size) { 0f })
    val horseProgresses = _horseProgresses.asStateFlow()

    private val _winner = MutableStateFlow<Int?>(null)
    val winner = _winner.asStateFlow()



    fun startRace() {
        if(_raceState.value != RaceState.IDLE) return

        viewModelScope.launch {
            _raceState.value = RaceState.RUNNING

            _winner.value = null
            val horseCount = horseNumbers.size

            val progresses = MutableList(horseCount) { 0f }
            _horseProgresses.value = progresses


            val durations = generateHorseSpeedsUseCase(horseCount)

            horseNumbers.forEachIndexed { index, horseNumber ->
                launch {
                    val steps = 100
                    //делим время каждой лошади на 100
                    val delayPerStep = durations[index] / steps
                    repeat(steps) {
                        if(!isActive || _winner.value != null) return@launch
                        delay(delayPerStep)
                        progresses[index] += 1f / steps
                        _horseProgresses.value = progresses.toList()
                    }

                    progresses[index] = 1f //гарантия что в конце будет 1f а не 0.9998f
                    _horseProgresses.value = progresses.toList()

                    // Победитель
                    if (_winner.value == null) {
                        _winner.value = horseNumber
                        _raceState.value = RaceState.FINISHED
                        saveRaceResult(horseNumber)

                    }
                }
            }
        }
    }

    //обнуляем забег
    fun restartRace() {
        _horseProgresses.value = List(horseNumbers.size) { 0f }
        _winner.value = null
        _raceState.value = RaceState.IDLE
    }

    private fun saveRaceResult(winner: Int) {
        viewModelScope.launch {
            insertRaceUseCase(winner = winner)
        }
    }

}