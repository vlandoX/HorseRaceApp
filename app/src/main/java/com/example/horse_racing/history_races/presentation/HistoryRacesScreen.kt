package com.example.horse_racing.history_races.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.horse_racing.history_races.presentation.components.ListRaceItem
import com.example.horse_racing.history_races.presentation.components.TopBar
import com.example.horse_racing.core.domain.model.RaceItem
import com.example.horse_racing.ui.theme.StableBeige


@Composable
fun HistoryRacesScreenRoot(
    historyRaceViewModel: HistoryRaceViewModel = hiltViewModel(),
) {
    val raceListState by historyRaceViewModel.raceListState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        historyRaceViewModel.loadRaces()
    }

    HistoryRacesScreen(
        raceListState = raceListState,
        deleteRace = historyRaceViewModel::deleteRace,
    )
}

@Composable
fun HistoryRacesScreen(
    raceListState: List<RaceItem>,
    deleteRace: (race: RaceItem) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(raceListState) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(StableBeige)
                .padding(top = paddingValues.calculateTopPadding()),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {

            items(
                count = raceListState.size,
                key = { it }
            ) { index ->
                ListRaceItem(
                    raceItem = raceListState[index],
                    onDelete = {
                        deleteRace(raceListState[index])
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
    val raceItems =
        listOf(
            RaceItem(
                id = 1,
                winner = 1,
                dateRace = 1L
            ),
            RaceItem(
                id = 2,
                winner = 3,
                dateRace = 2L
            ),
            RaceItem(
                id = 3,
                winner = 2,
                dateRace = 3L
            )
        )
    MaterialTheme {
        HistoryRacesScreen(
            raceListState = raceItems,
            deleteRace = { },
        )

    }
}