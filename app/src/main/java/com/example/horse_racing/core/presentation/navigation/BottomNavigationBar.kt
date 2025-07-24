package com.example.horse_racing.core.presentation.navigation


import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.NavigationBarItem

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.navigation.NavGraph.Companion.findStartDestination


@Composable
fun BottomBar(
    navController: NavController
) {
    val currentRoute = destinationRoute(navController)

    NavigationBar {
        NavBarItems.barItems.forEach{ item ->
            val isSelected = currentRoute == item.screen.route
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },

                label = {Text(item.label)}
            )
        }
    }



}
@Composable
private fun destinationRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

