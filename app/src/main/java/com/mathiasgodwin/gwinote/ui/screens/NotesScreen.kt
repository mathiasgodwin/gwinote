package com.mathiasgodwin.gwinote.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mathiasgodwin.gwinote.navigations.Screen
import com.mathiasgodwin.gwinote.ui.components.AppDrawer
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(Closed)
    val scope = rememberCoroutineScope()

    // Track current route
    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)
        .value?.destination?.route ?: Screen.Notes.route



    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = DrawerDefaults.modalContainerColor,
        drawerContent = {
            AppDrawer(
                currentRoute = currentRoute,
                modifier = Modifier.systemBarsPadding(),
                navigateToHome = {
                    navController.navigate(Screen.Notes.route) {
                        popUpTo(Screen.Notes.route) { inclusive = true }
                    }
                },
                navigateToTrash = {
                    navController.navigate(Screen.Trash.route) {
                        popUpTo(Screen.Notes.route)
                    }
                },
                closeDrawer = { scope.launch { drawerState.close() } }
            )

        }
    ) {
        val colorScheme = MaterialTheme.colorScheme

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Gwinote") },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(Screen.SaveNote.route)
                        }) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = "Add Note",
                                tint = colorScheme.primary
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                            )
                        }
                    }
                )

            }
        ) { paddingValues ->
            Text(
                "Notes Screen",
                modifier = Modifier
                    .padding(paddingValues)
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
            )
        }
    }
}
