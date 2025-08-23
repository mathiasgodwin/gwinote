package com.mathiasgodwin.gwinote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.mathiasgodwin.gwinote.navigations.AppNavHost
import com.mathiasgodwin.gwinote.navigations.Screen
import com.mathiasgodwin.gwinote.ui.theme.GwinoteTheme
import com.mathiasgodwin.gwinote.viewmodel.NotesViewModel
import com.mathiasgodwin.gwinote.viewmodel.NotesViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
        )
        val viewModelFactory = NotesViewModelFactory(application)

        setContent {
            GwinoteTheme {
                val navController = rememberNavController()
                val notesViewModel: NotesViewModel = viewModel(factory = viewModelFactory)

                AppNavHost(
                    notesViewModel = notesViewModel,
                    navController = navController,
                    startDestination = Screen.Notes.route,
                    modifier = Modifier
                );
            }
        }
    }
}

