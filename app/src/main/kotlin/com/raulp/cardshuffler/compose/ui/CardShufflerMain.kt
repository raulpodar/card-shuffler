

package com.raulp.cardshuffler.compose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme
import com.raulp.cardshuffler.compose.core.navigation.AppComposeNavigator
import com.raulp.cardshuffler.compose.core.navigation.CardShufflerScreen
import com.raulp.cardshuffler.compose.navigation.CardShufflerNavHost

// Data class for navigation items
data class NavItem(
    val screen: CardShufflerScreen,
    val label: String,
    val icon: ImageVector
)

@Composable
fun CardShufflerMain(composeNavigator: AppComposeNavigator<CardShufflerScreen>) {
    CardShufflerTheme {
        val navHostController = rememberNavController()

        LaunchedEffect(Unit) {
            composeNavigator.handleNavigationCommands(navHostController)
        }

        val navigationItems = listOf(
            NavItem(CardShufflerScreen.Home, "Home", Icons.Filled.Home),
            NavItem(CardShufflerScreen.CardList, "Card List", Icons.Filled.List), // Changed line
            NavItem(CardShufflerScreen.SettingsScreen, "Settings", Icons.Filled.Settings)
        )

        Scaffold(
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    navigationItems.forEach { item ->
                        // Assuming item is NavItem(screen: CardShufflerScreen, label: String, icon: ImageVector)
                        // And CardShufflerScreen.Home, .CardList, .SettingsScreen now have a .route property
                        NavigationBarItem(
                            selected = currentDestination?.route == item.screen.route, // Changed
                            onClick = {
                                navHostController.navigate(item.screen.route) { // Changed
                                    popUpTo(navHostController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            CardShufflerNavHost(
                navHostController = navHostController,
                modifier = Modifier.padding(innerPadding) // Apply padding
            )
        }
    }
}
