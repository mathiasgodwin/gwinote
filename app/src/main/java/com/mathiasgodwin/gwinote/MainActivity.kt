package com.mathiasgodwin.gwinote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mathiasgodwin.gwinote.navigations.AppNavHost
import com.mathiasgodwin.gwinote.navigations.Screen
import com.mathiasgodwin.gwinote.ui.components.AppDrawer
import com.mathiasgodwin.gwinote.ui.screens.NotesScreen
import com.mathiasgodwin.gwinote.ui.screens.SaveNoteScreen
import com.mathiasgodwin.gwinote.ui.screens.TrashScreen
import com.mathiasgodwin.gwinote.ui.theme.GwinoteTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
        )
        setContent {
            GwinoteTheme {
                val navController = rememberNavController()

                // Track current route
                val currentRoute = navController
                    .currentBackStackEntryFlow
                    .collectAsState(initial = navController.currentBackStackEntry)
                    .value?.destination?.route ?: Screen.Notes.route

                when (currentRoute) {
                    Screen.Notes.route -> NotesScreen()
                    Screen.Trash.route -> TrashScreen()
                    Screen.SaveNote.route -> SaveNoteScreen(navController = navController)
                }
            }

        }
    }
}


