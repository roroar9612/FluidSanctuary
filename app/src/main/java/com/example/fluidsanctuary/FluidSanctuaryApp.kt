package com.example.fluidsanctuary

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fluidsanctuary.components.BottomNavBar
import com.example.fluidsanctuary.screens.HistoryScreen
import com.example.fluidsanctuary.screens.HomeScreen
import com.example.fluidsanctuary.screens.SettingsScreen

// ─── Rutas ────────────────────────────────────────────────────────────────────
sealed class Screen(val route: String) {
    object Home     : Screen("home")
    object History  : Screen("history")
    object Settings : Screen("settings")
}

// ─── Raíz de la app ──────────────────────────────────────────────────────────
@Composable
fun FluidSanctuaryApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentDestination = currentDestination,
                onNavigate = { screen ->
                    navController.navigate(screen.route) {
                        // Evita apilar la misma pantalla varias veces
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController    = navController,
            startDestination = Screen.Home.route,
            modifier         = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route)     { HomeScreen() }
            composable(Screen.History.route)  { HistoryScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
        }
    }
}
