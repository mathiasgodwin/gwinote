package com.mathiasgodwin.gwinote.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mathiasgodwin.gwinote.ui.screens.NotesScreen
import com.mathiasgodwin.gwinote.ui.screens.SaveNoteScreen
import com.mathiasgodwin.gwinote.ui.screens.TrashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = Screen.Notes.route,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(Screen.Notes.route) {
            NotesScreen()
        }
        composable(Screen.Trash.route) {
            TrashScreen()
        }
        composable (Screen.SaveNote.route){
            SaveNoteScreen(
                navController = navController
            )
        }
    }
}
