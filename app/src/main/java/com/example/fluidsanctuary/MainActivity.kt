package com.example.fluidsanctuary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fluidsanctuary.ui.theme.FluidSanctuaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FluidSanctuaryTheme {
                FluidSanctuaryApp()
            }
        }
    }
}