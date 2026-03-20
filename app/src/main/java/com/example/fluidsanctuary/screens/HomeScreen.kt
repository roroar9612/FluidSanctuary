package com.example.fluidsanctuary.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import kotlin.math.PI

@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()

    // Estado local — después se conectará al ViewModel
    var glassesToday by remember { mutableIntStateOf(5) }
    val goal = 8
    val progress = glassesToday.toFloat() / goal.toFloat()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp, bottom = 32.dp)
    ) {

        // ── Hero: progreso asimétrico ─────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Indicador circular
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1.2f)
                    .aspectRatio(1f)
            ) {
                CircularProgress(
                    progress = progress,
                    primaryColor = MaterialTheme.colorScheme.primary,
                    tertiaryColor = MaterialTheme.colorScheme.tertiary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "$glassesToday",
                            fontSize = 52.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 52.sp,
                        )
                        Text(
                            text = "/$goal",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.outlineVariant,
                            modifier = Modifier.padding(bottom = 8.dp, start = 2.dp)
                        )
                    }
                    Text(
                        text = "GLASSES TAKEN",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 2.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            Spacer(Modifier.width(16.dp))

            // Cards de estado
            Column(
                modifier = Modifier.weight(0.8f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoCard(
                    label = "STATUS",
                    value = "You're ${(progress * 100).toInt()}% towards your goal. Keep flowing!",
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                    labelColor = MaterialTheme.colorScheme.primary,
                )
                InfoCard(
                    label = "NEXT REMINDER",
                    value = "14:30 • Hydration Break",
                    backgroundColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        // ── Botón primario ────────────────────────────────────────────────────
        Button(
            onClick = { if (glassesToday < goal) glassesToday++ },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(56.dp)
                .padding(horizontal = 8.dp)
        ) {
            Icon(Icons.Default.WaterDrop, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Add Glass",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(Modifier.height(32.dp))

        // ── Today's Log ───────────────────────────────────────────────────────
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Today's Log",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )
            TextButton(onClick = {}) {
                Text(
                    text = "View All",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // Card grande — Morning Refresh
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 1.dp,
            shadowElevation = 1.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Icon(
                        Icons.Default.LocalDrink,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }
                Spacer(Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Morning Refresh",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = "2 glasses • 08:45 AM",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // Cards pequeñas — bento grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BentoCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Schedule,
                label = "POST LUNCH",
                value = "12:30 PM",
            )
            BentoCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Bolt,
                label = "CURRENT STREAK",
                value = "12 Days",
            )
        }

        Spacer(Modifier.height(24.dp))

        // ── Sanctuary Wisdom ──────────────────────────────────────────────────
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFF0A3D52),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(28.dp)) {
                Text(
                    text = "SANCTUARY WISDOM",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 3.sp,
                    color = Color(0xFFB2EBF2),
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Drinking water improves cognitive focus.",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    lineHeight = 28.sp,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Proper hydration helps brain cells communicate, clears out toxins, and carries nutrients to your brain.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFB2EBF2).copy(alpha = 0.7f),
                    lineHeight = 18.sp,
                )
            }
        }
    }
}

// ── Componentes locales ───────────────────────────────────────────────────────

@Composable
private fun CircularProgress(
    progress: Float,
    primaryColor: Color,
    tertiaryColor: Color,
    trackColor: Color,
) {
    val sweepAngle = 280f * progress
    val brush = Brush.linearGradient(listOf(primaryColor, tertiaryColor))

    Canvas(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val strokeWidth = 36f
        val diameter = size.minDimension
        val topLeft = Offset(
            (size.width - diameter) / 2f,
            (size.height - diameter) / 2f
        )
        val arcSize = Size(diameter, diameter)
        val startAngle = 130f

        // Track
        drawArc(
            color = trackColor,
            startAngle = startAngle,
            sweepAngle = 280f,
            useCenter = false,
            topLeft = topLeft,
            size = arcSize,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
        // Progreso con gradiente
        drawArc(
            brush = brush,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = topLeft,
            size = arcSize,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
    }
}

@Composable
private fun InfoCard(
    label: String,
    value: String,
    backgroundColor: Color,
    labelColor: Color,
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = label,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = labelColor,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Composable
private fun BentoCard(
    modifier: Modifier,
    icon: ImageVector,
    label: String,
    value: String,
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = label,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}
