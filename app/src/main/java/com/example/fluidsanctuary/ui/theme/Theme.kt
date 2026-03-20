package com.example.fluidsanctuary.ui.theme


import com.example.fluidsanctuary.ui.theme.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val FluidColorScheme = lightColorScheme(
    primary              = Primary,
    onPrimary            = OnPrimary,
    primaryContainer     = PrimaryContainer,
    onPrimaryContainer   = OnPrimaryContainer,
    secondary            = Secondary,
    onSecondary          = OnSecondary,
    secondaryContainer   = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,
    tertiary             = Tertiary,
    onTertiary           = OnTertiary,
    background           = Background,
    onBackground         = OnBackground,
    surface              = Surface,
    onSurface            = OnSurface,
    onSurfaceVariant     = OnSurfaceVariant,
    outline              = Outline,
    outlineVariant       = OutlineVariant,
    error                = Error,
    onError              = OnError,
)

@Composable
fun FluidSanctuaryTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FluidColorScheme,
        typography  = FluidTypography,
        content     = content
    )
}