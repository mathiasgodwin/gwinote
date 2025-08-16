package com.mathiasgodwin.gwinote.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.mathiasgodwin.gwinote.ui.theme.GwinoteTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveNoteScreen(navController: NavHostController? = null) {
    val colorPickerSheetState = rememberModalBottomSheetState()
    var showColorPicker: Boolean by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Save note")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController?.popBackStack()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        showColorPicker = true
                    }) {
                        Icon(
                            Icons.Default.ShoppingCart,
                            contentDescription = "Color picker"
                        )
                    }
                },
            )
        },

        ) { it ->
        if (showColorPicker) {
            ModalBottomSheet(
                sheetState = colorPickerSheetState,
                onDismissRequest = { showColorPicker = false },
            ) {
                ColorItemListView()
            }
        }
    }
}

@Composable
private fun NewNoteForm(modifier: Modifier = Modifier) {
    val noteTitle = remember { mutableStateOf("") }
    val noteContent = remember { mutableStateOf("") }

    Column {
        Text("Note Title")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = noteTitle.value,
            label = { Text("Enter note title") },
            onValueChange = { noteTitle.value = it },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Note Content")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = noteContent.value,
            label = { Text("Enter note content") },
            onValueChange = { noteContent.value = it }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button (
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {}
        ) {
            Text("Save Note")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewNoteFormPreview() {
    GwinoteTheme {
        NewNoteForm()
    }
}

@Preview(showBackground = true)
@Composable
fun SaveNoteScreenPreview() {
    SaveNoteScreen()
}

@Composable
private fun ColorItemListView() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Color picker")
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(ColorDataList) { colorData ->
                ColorItem(colorData)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorItemListViewPreview() {
    ColorItemListView()
}

@Composable
private fun ColorItem(
    colorData: ColorData, onColorSelect: (ColorData) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .clickable(
                onClick = {
                    onColorSelect(colorData)
                },
            )
    ) {
        Box(
            modifier = Modifier
                .padding(
                    0.dp
                )
                .size(40.dp)
                .border(BorderStroke(1.dp, Color.Black), CircleShape)
                .background(
                    color = Color(colorData.hex.toColorInt()),
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(colorData.name)
    }
}


@Preview(showBackground = true)
@Composable
fun ColorItemPreview() {
    ColorItem(colorData = ColorDataList[2])
}

private data class ColorData(val id: Long, val hex: String, val name: String)

private val ColorDataList = listOf<ColorData>(
    ColorData(1, "#FFFFFF", "White"),
    ColorData(9, "#F44336", "Red"),
    ColorData(3, "#F06292", "Pink"),
    ColorData(4, "#2196F3", "Blue"),
    ColorData(5, "#4CAF50", "Green"),
    ColorData(6, "#8BC34A", "Light Green"),
    ColorData(7, "#FFEB3B", "Yellow"),
    ColorData(8, "#FF9800", "Orange"),
    ColorData(9, "#F44336", "Red"),
    ColorData(10, "#9C27B0", "Purple"),
    ColorData(11, "#673AB7", "Deep Purple"),
)
