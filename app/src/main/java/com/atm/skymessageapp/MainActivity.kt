package com.atm.skymessageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.atm.skymessageapp.ui.navigation.MainNavigation
import com.atm.skymessageapp.ui.theme.ColorWhite
import com.atm.skymessageapp.ui.theme.SkyMessageTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkyMessageTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = ColorWhite
                ) { innerPadding ->
                    MainNavigation(
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}