package com.mathiasgodwin.gwinote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mathiasgodwin.gwinote.navigations.AppNavHost
import com.mathiasgodwin.gwinote.navigations.Screen
import com.mathiasgodwin.gwinote.ui.theme.GwinoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
        )
        setContent {
            GwinoteTheme {
                val navController = rememberNavController()

                AppNavHost(
                    navController = navController,
                    startDestination = Screen.Notes.route,
                    modifier = Modifier
                );
            }
        }
    }
}

