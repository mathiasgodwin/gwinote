package com.mathiasgodwin.gwinote.navigations

sealed class Screen(val route: String) {
    data object Notes : Screen("notes")
    data object Trash : Screen("trash")
    data object SaveNote : Screen("save_note")
}
