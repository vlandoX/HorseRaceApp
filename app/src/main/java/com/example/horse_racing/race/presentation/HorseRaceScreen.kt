package com.example.horse_racing.race.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.horse_racing.race.presentation.components.Header
import com.example.notesapptesting.R
import com.example.horse_racing.ui.theme.RestartOrange
import com.example.horse_racing.ui.theme.StableBeige
import com.example.horse_racing.ui.theme.StartGreen

@Composable
fun HorseRaceScreenRoot(
    viewModel: RaceViewModel = hiltViewModel(),
) {
    val raceState by viewModel.raceState.collectAsStateWithLifecycle()
    val winner by viewModel.winner.collectAsStateWithLifecycle()
    val progresses by viewModel.horseProgresses.collectAsStateWithLifecycle()
    val horseNumbers = viewModel.horseNumbers

    HorseRaceScreen(
        raceState = raceState,
        winner = winner,
        progresses = progresses,
        horseNumbers = horseNumbers,
        startRace = { viewModel.startRace() },
        restartRace = { viewModel.restartRace() }
    )
}

@Composable
fun HorseRaceScreen(
    raceState: RaceState,
    winner: Int?,
    progresses: List<Float>,
    horseNumbers: List<Int>,
    startRace: () -> Unit,
    restartRace: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StableBeige)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        Spacer(modifier = Modifier.height(16.dp))

        horseNumbers.forEachIndexed { index, number ->
            HorseRace(
                progress = progresses.getOrNull(index) ?: 0f,
                horseNumber = number
            )
        }

        Button(
            modifier = Modifier
                .padding(top = 24.dp, bottom = 8.dp),
            onClick = { startRace() },
            enabled = raceState == RaceState.IDLE,
            colors = ButtonDefaults.buttonColors(
                containerColor = StartGreen,  // основной цвет кнопки
            )
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 4.dp),
                text = stringResource(R.string.start),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.SemiBold,
            )
        }

        if (raceState == RaceState.FINISHED) {
            Button(
                onClick = { restartRace() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = RestartOrange,
                )
            )
            {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 4.dp),
                    text = stringResource(R.string.restart),
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        winner?.let {
            Text(
                text = "\uD83C\uDFC6 Победитель: Лошадь №$it",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun HorseRace(
    progress: Float,
    horseNumber: Int
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {

        Text(text = "Лошадь №$horseNumber", fontSize = 18.sp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(vertical = 4.dp)
        ) {
            LinearProgressIndicator(
                progress = progress.coerceIn(0f, 1f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .align(Alignment.CenterStart),
                color = StartGreen,
                trackColor = Color.LightGray,
                strokeCap = StrokeCap.Round
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "\uD83D\uDC0E",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .scale(scaleX = -1f, scaleY = 1f) //зеркалит икноку
                )
            }

        }
    }
}




@Preview(showBackground = true)
@Composable
fun TestAddNote() {

    HorseRaceScreen(
        raceState = RaceState.FINISHED,
        winner = 1,
        horseNumbers = listOf(1, 2, 3),
        progresses = listOf(1f, 0.3f, 0.6f),
        startRace = {},
        restartRace = {}
    )
}