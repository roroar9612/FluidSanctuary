package com.example.fluidsanctuary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.fluidsanctuary.Screen

// ─── Modelo de cada tab ──────────────────────────────────────────────────────
private data class NavItem(
    val screen: Screen,
    val label: String,
    val icon: ImageVector,
)

private val navItems = listOf(
    NavItem(Screen.Home,     "Home",     Icons.Default.Home),
    NavItem(Screen.History,  "History",  Icons.Default.DateRange),
    NavItem(Screen.Settings, "Settings", Icons.Default.Settings),
)

// ─── Componente ──────────────────────────────────────────────────────────────
@Composable
fun BottomNavBar(
    currentDestination: NavDestination?,
    onNavigate: (Screen) -> Unit,
) {
    Surface(
        tonalElevation = 0.dp,
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        shadowElevation = 8.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 32.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment     = Alignment.CenterVertically,
        ) {
            navItems.forEach { item ->
                val isSelected = currentDestination
                    ?.hierarchy
                    ?.any { it.route == item.screen.route } == true

                NavTab(
                    item       = item,
                    isSelected = isSelected,
                    onClick    = { onNavigate(item.screen) },
                )
            }
        }
    }
}

// ─── Tab individual ──────────────────────────────────────────────────────────
@Composable
private fun NavTab(
    item: NavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    // El tab activo tiene fondo circular elevado, igual que en el HTML
    if (isSelected) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable(onClick = onClick)
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .offset(y = (-6).dp),
        ) {
            Icon(
                imageVector        = item.icon,
                contentDescription = item.label,
                tint               = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier           = Modifier.size(24.dp),
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text  = item.label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable(onClick = onClick)
                .padding(horizontal = 16.dp, vertical = 10.dp),
        ) {
            Icon(
                imageVector        = item.icon,
                contentDescription = item.label,
                tint               = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier           = Modifier.size(24.dp),
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text  = item.label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
