package com.example.horse_racing.history_races.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horse_racing.core.domain.model.RaceItem
import com.example.horse_racing.history_races.domain.use_case.DeleteRaceUseCase
import com.example.horse_racing.history_races.domain.use_case.GetAllRacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryRaceViewModel @Inject constructor(
    private val getAllRacesUseCase: GetAllRacesUseCase,
    private val deleteRaceUseCase: DeleteRaceUseCase,
) : ViewModel() {

    private val _raceListState = MutableStateFlow<List<RaceItem>>(emptyList())
    val raceListState = _raceListState.asStateFlow()
    


    fun loadRaces() {
        viewModelScope.launch {
            _raceListState.update {
                getAllRacesUseCase()
            }
        }
    }

    fun deleteRace(raceItem: RaceItem) {
        viewModelScope.launch {
            deleteRaceUseCase(raceItem)
            loadRaces()
        }
    }
    
}