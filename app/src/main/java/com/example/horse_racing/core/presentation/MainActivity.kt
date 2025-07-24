package com.example.horse_racing.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.horse_racing.core.presentation.navigation.BottomBar
import com.example.horse_racing.core.presentation.navigation.NavRoutes
import com.example.horse_racing.race.presentation.HorseRaceScreenRoot
import com.example.horse_racing.history_races.presentation.HistoryRacesScreenRoot
import com.example.horse_racing.ui.theme.NotesAppTestingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTestingTheme {
                Navigation()
            }
        }
    }

    @Composable
    fun Navigation(){
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomBar(navController = navController)
            }
        ) { paddingValues ->

            NavHost(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
                startDestination = NavRoutes.RaceScreen.route
            ){

                composable(NavRoutes.RaceScreen.route) {
                    HorseRaceScreenRoot()
                }

                composable(NavRoutes.HistoryRacesScreen.route) {
                    HistoryRacesScreenRoot()
                }


            }
        }
    }
}



